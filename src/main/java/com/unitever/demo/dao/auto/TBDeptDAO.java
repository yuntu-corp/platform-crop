package com.unitever.demo.dao.auto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.unitever.demo.model.TBDept;
@Component
public interface TBDeptDAO {
    
    public void save(TBDept dept);  
      
    public void update(TBDept dept);  
      
    public void delete(String id);  
      
    public TBDept get(String id);  
    
    public List<TBDept> getAll();  
}
