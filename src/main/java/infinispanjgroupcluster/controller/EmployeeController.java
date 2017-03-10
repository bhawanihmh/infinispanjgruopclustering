package infinispanjgroupcluster.controller;

import java.util.Iterator;

import org.infinispan.CacheCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinispanjgroupcluster.InfinispanJgroupCluster;
import infinispanjgroupcluster.bean.EmployeeBean;
/**
 * 
 * @author bhawani.singh
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	private InfinispanJgroupCluster infinispan;
	
	private String empId ;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/getEmployeeHtml", method = RequestMethod.GET)
	public String getEmployeeHtml(ModelMap model) {
		System.out.println(infinispan.getManager().getCache());
		if(empId != null){
			EmployeeBean emp = (EmployeeBean)infinispan.getManager().getCache().get(empId);
			model.put("employee", emp);
		}
		return "employee";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/addEmployee", method = RequestMethod.GET)
	public String addEmployee(ModelMap model) {
		empId = (Math.random()) +"";
		EmployeeBean employee = new EmployeeBean();
		employee.setId(empId);
		employee.setName("Nidhi Jain");
		employee.setRole("PL");
		infinispan.getManager().getCache().put(empId, employee);
		
		CacheCollection<Object> employees = infinispan.getManager().getCache().values();
		Iterator empItr = employees.iterator();
		while (empItr.hasNext()) {
			EmployeeBean emp = (EmployeeBean) empItr.next();
			System.out.println("Employee Name :" + emp.getName() + " , id = " + emp.getId());
		}
		
		return "redirect:getEmployeeHtml";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/deleteEmployee", method = RequestMethod.GET)
	public String deleteEmployee(ModelMap model) {
		infinispan.getManager().getCache().remove(empId);
		empId = null;
		return "redirect:getEmployeeHtml";
	}

}
