/**
 * 
 */
package cn.sina.api.commons.cache.driver;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import cn.sina.api.commons.cache.CasValue;
import cn.sina.api.commons.cache.MemcacheClient;

/**
 * @author yuanming@staff.sina.com.cn
 * @author yangwm Mar 22, 2013 12:15:01 AM
 * 
 * a mock MemcacheClient implement. 
 *
 */
public class MockClient implements MemcacheClient {
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	private int minSpareConnections;
	private int maxSpareConnections;
	
    public void init() {
    }
	

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#setServerPort(java.lang.String)
	 */
	@Override
	public void setServerPort(String serverPort) {
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#setServerPortList(java.lang.String[])
	 */
	@Override
	public void setServerPortList(String[] serverPort) {
	}
	
	

	public int getMinSpareConnections() {
		return minSpareConnections;
	}

	public void setMinSpareConnections(int minSpareConnections) {
		this.minSpareConnections = minSpareConnections;
	}

	public int getMaxSpareConnections() {
		return maxSpareConnections;
	}

	public void setMaxSpareConnections(int maxSpareConnections) {
		this.maxSpareConnections = maxSpareConnections;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean set(String key, Object value) {
		map.put(key, value);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#set(java.lang.String, java.lang.Object, java.util.Date)
	 */
	@Override
	public boolean set(String key, Object value, Date expdate) {
		map.put(key, value);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#setCas(java.lang.String, cn.vika.memcached.CasValue)
	 */
	@Override
	public boolean setCas(String key, CasValue<Object> casValue) {
		map.put(key, casValue.getValue());
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#setCas(java.lang.String, cn.vika.memcached.CasValue, java.util.Date)
	 */
	@Override
	public boolean setCas(String key, CasValue<Object> casValue, Date expdate) {
		map.put(key, casValue.getValue());
		return true;
	}
	
    // ----------- fix setCas bug ----------------- 
    public boolean cas(String key, CasValue<Object> casValue) {
        return this.setCas(key, casValue);
    }

    public boolean cas(String key, CasValue<Object> casValue, Date expdate) {
        return this.setCas(key, casValue, expdate);
    }
    
	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#append(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean append(String key, Object value) {
		map.put(key, value);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#get(java.lang.String)
	 */
	@Override
	public Object get(String key) {
		return map.get(key);
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String key) {
		map.remove(key);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#getMulti(java.lang.String[])
	 */
	@Override
	public Map<String, Object> getMulti(String[] keys) {
		Map<String,Object> result = new HashMap<String,Object>();
		for(String key:keys){
			result.put(key, this.get(key));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#add(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean add(String key, Object value) {
		this.map.put(key, value);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#add(java.lang.String, java.lang.Object, java.util.Date)
	 */
	@Override
	public boolean add(String key, Object value, Date expdate) {
		this.map.put(key, value);
		return true;
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#incr(java.lang.String)
	 */
	@Override
	public long incr(String key) {
		try{
			AtomicLong value = (AtomicLong)this.map.get(key);
			if(value == null){
				value = new AtomicLong(0);
				this.map.put(key, value);
			}
			return value.addAndGet(1);
		}catch(Exception e){
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#incr(java.lang.String, long)
	 */
	@Override
	public long incr(String key, long inc) {
		try{
			AtomicLong value = (AtomicLong)this.map.get(key);
			if(value == null){
				value = new AtomicLong(0);
				this.map.put(key, value);
			}
			return value.addAndGet(inc);
		}catch(Exception e){
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#decr(java.lang.String)
	 */
	@Override
	public long decr(String key) {
		try{
			AtomicLong value = (AtomicLong)this.map.get(key);
			if(value == null){
				value = new AtomicLong(0);
				this.map.put(key, value);
			}
			return value.addAndGet(-1);
		}catch(Exception e){
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#decr(java.lang.String, int)
	 */
	@Override
	public long decr(String key, int inc) {
		try{
			AtomicLong value = (AtomicLong)this.map.get(key);
			if(value == null){
				value = new AtomicLong(0);
				this.map.put(key, value);
			}
			return value.addAndGet(-inc);
		}catch(Exception e){
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#gets(java.lang.String)
	 */
	@Override
	public CasValue<Object> gets(String key) {
		return new CasValue<Object>(map.get(key));
	}

	/* (non-Javadoc)
	 * @see cn.sina.api.commons.cache.MemcacheClient#getsMulti(java.lang.String[])
	 */
	@Override
	public Map<String, Object> getsMulti(String[] keys) {
		Map<String,Object> result = new HashMap<String,Object>();
		for(String key:keys){
			result.put(key, this.get(key));
		}
		return result;
	}

}
