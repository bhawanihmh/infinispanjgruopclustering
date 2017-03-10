package infinispanjgroupcluster;

import java.util.concurrent.TimeUnit;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.stereotype.Component;
/**
 * 
 * @author bhawani.singh
 *
 */
@Component
public class InfinispanJgroupCluster {
	private EmbeddedCacheManager manager;
	
	public  InfinispanJgroupCluster() {		
		GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
		//Clustering  : distributed synchronous cache  :DIST_SYNC 
		//And Making  entries  expire
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		Configuration config = configBuilder.expiration().lifespan(30, TimeUnit.SECONDS)
				.clustering().cacheMode(CacheMode.DIST_ASYNC).build();
		global.transport().transport().defaultTransport()
		   .addProperty("configurationFile", "jgroups.xml").clusterName("WeatherApp");
		manager = new DefaultCacheManager(global.build(),config);
	}
	
	/**
	 * 
	 * @return
	 */
	public EmbeddedCacheManager getManager() {
		return manager;
	}
	
	/**
	 * 
	 * @param manager
	 */
	public void setManager(EmbeddedCacheManager manager) {
		this.manager = manager;
	}
	
	
}
