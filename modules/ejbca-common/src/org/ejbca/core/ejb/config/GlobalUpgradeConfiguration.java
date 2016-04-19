/*************************************************************************
 *                                                                       *
 *  EJBCA Community: The OpenSource Certificate Authority                *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General Public           *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/
package org.ejbca.core.ejb.config;

import java.util.Properties;

import org.cesecore.configuration.ConfigurationBase;

/**
 * Object for keeping track of database content version.
 * 
 * @version $Id: GlobalUpgradeConfiguration.java 22117 2015-10-29 10:53:42Z mikekushner $
 */
public class GlobalUpgradeConfiguration extends ConfigurationBase {

    public static final String CONFIGURATION_ID = "UPGRADE";
   
    private static final long serialVersionUID = 1L;

    private static final String UPGRADED_TO_VERSION = "upgradedToVersion";
    private static final String POST_UPGRADED_TO_VERSION = "postUpgradedToVersion";
    
    public String getUpgradedToVersion() {
        return (String) data.get(UPGRADED_TO_VERSION);
    }
    public void setUpgradedToVersion(final String version) {
        data.put(UPGRADED_TO_VERSION, version);
    }

    public String getPostUpgradedToVersion() {
        return (String) data.get(POST_UPGRADED_TO_VERSION);
    }
    public void setPostUpgradedToVersion(final String version) {
        data.put(POST_UPGRADED_TO_VERSION, version);
    }

    @Override
    public void upgrade() {
        if(Float.compare(LATEST_VERSION, getVersion()) != 0) {
            data.put(VERSION,  Float.valueOf(LATEST_VERSION));          
        }
    }

    @Override
    public String getConfigurationId() {
        return CONFIGURATION_ID;
    }

    public Properties getAsProperties() {
        final Properties properties = new Properties();
        properties.put(UPGRADED_TO_VERSION, getUpgradedToVersion());
        properties.put(POST_UPGRADED_TO_VERSION, getPostUpgradedToVersion());
        return properties;
    }
}
