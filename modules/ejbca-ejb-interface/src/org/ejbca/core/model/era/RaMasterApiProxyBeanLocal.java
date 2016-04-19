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
package org.ejbca.core.model.era;

import javax.ejb.Local;

/**
 * Interface for EJB access to the RaMasterApi proxy singleton
 * 
 * @version $Id: RaMasterApiProxyBeanLocal.java 23192 2016-04-13 14:28:49Z jeklund $
 */
@Local
public interface RaMasterApiProxyBeanLocal extends RaMasterApi {

}
