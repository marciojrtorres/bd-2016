package artur.dao;

import java.util.*;

public interface IDAO<TYPE> {
	public void insert(TYPE t);
	
	public void delete(Integer id);
	
	public void delete(TYPE t);
	
	public List<TYPE> selectAll();
	
	public List<TYPE> selectPage(Integer page);
	
	public TYPE select(Integer id);
	
	public void update(TYPE t);
}