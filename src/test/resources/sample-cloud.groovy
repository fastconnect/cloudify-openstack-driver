
cloud {
	// Mandatory. The name of the cloud, as it will appear in the Cloudify UI.
	name = "sample"
	configuration {
		// Mandatory - openstack Essex cloud driver.
		className "org.cloudifysource.esc.driver.provisioning.openstack2.OpenstackCloudDriver"
		// Optional. The template name for the management machines. Defaults to the first template in the templates section below.
		managementMachineTemplate "MEDIUM_LINUX"
		// Optional. Indicates whether internal cluster communications should use the machine private IP. Defaults to true.
		connectToPrivateIp true
	}

	provider {
		// optional 
		provider "openstack"
		
		cloudifyUrl "http://repository.cloudifysource.org/org/cloudifysource/2.2.0-RELEASE/gigaspaces-cloudify-2.2.0-ga-b2500" 
		// create a archive with the driver in folder lib/plateform/esm
		//cloudifyOverridesUrl "https://github.com/downloads/fastconnect/cloudify-openstack-driver/gigaspaces_overrides"		

		// warning: '.' is not allowed
		machineNamePrefix "cloudify-agent-"
		
		dedicatedManagementMachines true
		managementOnlyFiles ([])
		
		managementGroup "cloudify-manager"
		numberOfManagementMachines 1
		zones (["agent"])
		reservedMemoryCapacityPerMachineInMB 1024
		
		sshLoggingLevel "WARNING"
		
		
	}
	user {
		user "LOGIN"
		apiKey "PASSWORD"		
	}
	templates ([
				MEDIUM_LINUX : template{
					// use "nova image-list" to find the imageId
					imageId "RegionOne/4551b784-99ae-4593-b5c6-2c9b2e946142"
					machineMemoryMB 1600
					// use "nova flavor-list" to find the id of the hardware
					hardwareId "RegionOne/3"
					remoteDirectory "/root/"
					
					localDirectory "upload"
					
					username="USERNAME"
										
					options ([
						"openstack.securityGroup" : "default",
						"openstack.keyPair" : "KEYPAIR_NAME"
					])
					
					//privileged true
					
				}
			])
			
	custom ([	
		"openstack.identity.endpoint": "http://ENDPOINT:5000/",
		"openstack.tenantName" : "TENANTNAME",
		// "password" for Essex, but for older version you can you "apiAccessKey"
		// in this case, the "apiKey" will be the OpenStack secretKey
		// and the "user" will be OpenStack accessKey
		"openstack.credentialType" : "password",
		"openstack.wireLog": "false"

	])
}

