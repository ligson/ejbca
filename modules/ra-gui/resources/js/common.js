/*************************************************************************
 *                                                                       *
 *  EJBCA: The OpenSource Certificate Authority                          *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General Public           *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/

/* version: $Id: common.js 23229 2016-04-18 15:17:20Z jeklund $ */

"use strict";

// Executed when the document had been loaded
document.addEventListener("DOMContentLoaded", function(event) {
    console.log("Document loaded.");
    hideInputElementsByStyleClass("hideWithJs");
    new SessionKeepAlive("sessionKeepAliveLink");
}, false);

/** Keep JSF session alive by polling back-end before the session has expired */
function SessionKeepAlive(linkElementId) {
    var instance = this;
    this.timeToNextCheckInMs = 100; // Make first check after 100 ms.
    this.xmlHttpReq = new XMLHttpRequest();
    var linkComponent = document.getElementById(linkElementId);
    if (linkComponent) {
        this.link = linkComponent.getAttribute("href");
    }
    this.xmlHttpReq.onreadystatechange = function() {
        if (instance.xmlHttpReq.readyState == 4) {
            if (instance.xmlHttpReq.status == 200) {
                instance.timeToNextCheckInMs = instance.xmlHttpReq.responseText;
                setTimeout(instance.poll, instance.timeToNextCheckInMs);
        	} else {
                console.log("SessionKeepAlive failed with HTTP status code " + instance.xmlHttpReq.status);
        	}
        }
    };
    this.poll = function() {
    	instance.xmlHttpReq.open("GET", instance.link, true);
        try {
        	instance.xmlHttpReq.send();
        } catch (exception) {
            console.log("SessionKeepAlive failed: " + exception);
        }
    };
    if (this.link) {
        setTimeout(this.poll, this.timeToNextCheckInMs);
    } else {
        console.log("Unable to find link element with id " + linkElementId + ". SessionKeepAlive will not be enabled.");
    }
};

/** Hide all input elements marked with the styleClassName. */
function hideInputElementsByStyleClass(styleClassName) {
	var inputFields = document.getElementsByTagName("input");
	for (var i = 0; i<inputFields.length; i++) {
		if (inputFields[i].className) {
			var styleClasses = inputFields[i].className.split(' ');
			for (var j = 0; j<styleClasses.length; j++) {
				if (styleClasses[j]==styleClassName) {
					inputFields[i].style.display = "none";
					break;
				}
			}
		}
	}
}
