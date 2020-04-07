package cn.wl.logistic.controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.wl.logistic.mo.MessageObject;
import cn.wl.logistic.pojo.BasicData;
import cn.wl.logistic.pojo.BasicDataExample;
import cn.wl.logistic.pojo.BasicDataExample.Criteria;
import cn.wl.logistic.service.BasicDataService;

@Controller
@RequestMapping("/basicData")
public class BasicDataController {
	
	@Autowired
	private BasicDataService basicDataService;
	
	@RequestMapping("/basicDataPage")
	@RequiresPermissions("basicData:basicDataPage")
	public String basicDataPage() {
		return "basicDataPage";
	}
	
	@RequestMapping("/list")
	@RequiresPermissions("basicData:list")
	@ResponseBody
	public PageInfo<BasicData> list(String keyWord,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "10")Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		BasicDataExample example = new BasicDataExample();
		if(StringUtils.isNotBlank(keyWord)) {
			Criteria criteria1 = example.createCriteria();
			criteria1.andBaseNameLike("%"+keyWord+"%");
			Criteria criteria2 = example.createCriteria();
			criteria2.andBaseDescLike("%"+keyWord+"%");
			example.or(criteria2);
		}
		List<BasicData> basicDatas = basicDataService.selectByExample(example);
		PageInfo<BasicData> info = new PageInfo<>(basicDatas);
		return info;
	}
	
//	删除的方法
	@RequestMapping("/delete")
	@RequiresPermissions("basicData:delete")
	@ResponseBody
	public MessageObject delete(Long baseId) {
		BasicDataExample example = new BasicDataExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(baseId);
		List<BasicData> children = basicDataService.selectByExample(example );
		if(children.size() >0) {
			MessageObject mo = new MessageObject(0, "当前数据还有子数据，不能删除");
			return mo;
		}
		int row = basicDataService.deleteByPrimaryKey(baseId);

		MessageObject mo = new MessageObject(0, "删除数据失败，请联系管理员");
		if (row == 1) {
			mo = new MessageObject(1, "删除数据成功");
		}
		return mo;
	}
	
//	批量删除管理员的方法
	@RequestMapping("/deleteAll")
	@ResponseBody
	public MessageObject deleteAll(@RequestParam(value = "ids[]") Long[] ids) {
		MessageObject mo = null;
		BasicDataExample example = new BasicDataExample();
		List<BasicData> basicDatas = basicDataService.selectByExample(example);
		boolean flag = true;
		int row = 0;
		for (int i = 0; i < ids.length; i++) {
			int result = basicDataService.deleteByPrimaryKey(ids[i]);
			if (result == 1) {
				row += 1;
			}
			for (int j = 0; j < basicDatas.size(); j++) {
				Long parentId = basicDatas.get(j).getParentId();
				if(parentId == ids[i]) {
					flag = false;
					break;
				}
			}
		}
		if(flag) {
			if (row >= 1) {
				mo = new MessageObject(1, "删除成功");
			} else {
				mo = new MessageObject(0, "删除失败，请联系管理员");
			}
			
		}else {
			mo = new MessageObject(2, "您要删除的数据中还有子数据存在，不能删除！");
		}
		return mo;
	}


	@RequestMapping("/checkBaseName")
	@ResponseBody
	public boolean checkBaseName(String baseName) {
		BasicDataExample example = new BasicDataExample();
		Criteria criteria = example.createCriteria();
		criteria.andBaseNameEqualTo(baseName);
		List<BasicData> BasicDatas = basicDataService.selectByExample(example);
		if (BasicDatas.size() == 1) {
			return false;
		} else {
			return true;
		}
	}
   
	@RequestMapping("/insert")
	@RequiresPermissions("basicData:insert")
	@ResponseBody
	public MessageObject insert(BasicData basicData) {
		int result = basicDataService.insert(basicData);
		MessageObject mo = null;
		if (result == 1) {
			mo = new MessageObject(1, "新增成功");
		} else {
			mo = new MessageObject(0, "新增操作失败");
		}
		return mo;
	}
	@RequestMapping("/edit")
	public String edit(Model m, Long baseId) {

	  if (baseId != null) {
			BasicData basicData = basicDataService.selectByPrimaryKey(baseId);
			m.addAttribute("basicData", basicData);
		}

		BasicDataExample example = new BasicDataExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdIsNull();
		List<BasicData> parents = basicDataService.selectByExample(example);
		
		m.addAttribute("parents", parents);
		return "basicDataEdit";
	}

	@RequestMapping("/update")
	@RequiresPermissions("basicData:update")
	@ResponseBody
	public MessageObject editAdmin(BasicData BasicData) {
		int row = basicDataService.updateByPrimaryKeySelective(BasicData);
		MessageObject mo = null;
		if (row == 1) {
			mo = new MessageObject(1, "信息修改成功");
		} else {
			mo = new MessageObject(0, "信息修改失败");
		}
		return mo;
	}

}
