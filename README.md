This driver is a Fork of the official driver included in Cloudify.

To avoid conflicts, I use a new package: org.cloudifysource.esc.driver.provisioning.openstack2

This driver works with ESSEX version of OpenStack, but it's also compatible with older versions.
It has a better configuration:

'''
custom ([	
		"openstack.identity.endpoint": "http://ENDPOINT:5000/",
		"openstack.tenantName" : "TENANTNAME",
		// "password" for Essex, but for older version you can you "apiAccessKey"
		// in this case, the "apiKey" will be the OpenStack secretKey
		// and the "user" will be OpenStack accessKey
		"openstack.credentialType" : "password",
		"openstack.wireLog": "false"

	])
'''

You can find a full example of the configuration in src/test/resources

This driver also create a Floting IP automatically, and release it automatically.
It does that because OpenStack don't create automatically a Public IP as in EC2, so we have to do it thanks to Floating IP.

You can download the driver from our Nexus, and package it in a gigaspaces_overrides.zip (you can use maven:assembly to do that, but you should exclude cloudify dependencies for a lighter archive)
The use of gigaspaces_overrides is explained here: http://www.cloudifysource.org/guide/2.1/clouddrivers/tutorial_maven (Packing and Adding to Cloudify)

Here is the POM configuration to include the driver:
'''
<repositories>
	<repository>
		<id>repo.opensource.fastconnect.org</id>
		<url>http://opensource.fastconnect.org/maven/content/repositories/opensource</url>
	</repository>
</repositories>

<dependencies>
	<dependency>
		<groupId>org.cloudifysource</groupId>
		<artifactId>cloudify-openstack-driver</artifactId>
		<version>1.1</version>
		<exclusions>
			<exclusion>
				<artifactId>esc</artifactId>
				<groupId>org.cloudifysource</groupId>
			</exclusion>
			<exclusion>
				<artifactId>dsl</artifactId>
				<groupId>org.cloudifysource</groupId>
			</exclusion>
		</exclusions>
	</dependency>
</dependencies>
'''

Here is the full URL: https://opensource.fastconnect.org/maven/content/repositories/opensource/org/cloudifysource/cloudify-openstack-driver/1.1/cloudify-openstack-driver-1.1.jar
