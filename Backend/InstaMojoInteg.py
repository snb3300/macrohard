from instamojo import Instamojo
import random

# Initialize the InstaMojo API object.
instamojo = Instamojo(api_key='ccf1719508ff93f0c181054be930e38a',
                auth_token='4dd3c64ff11fa3e204d49e474450b487')
print 'Initialized Instamojo API'

# Generates auth token - This will not work since we are not approved.
try:
	print '\nGenerating an Auth Token: '
	authToken = instamojo.auth('macrohard', 'macrohard')
	print authToken
except Exception as e:
	print e

# Gets a list of all Links that you have created and display their URLs.
print '\nGetting URLs of all payment links:'
response = instamojo.links_list()
links = response['links']
print 'You have ' + str(len(links)) + ' payment links!'
for link in links:
    print link['url']

# Getting link details
print '\nGetting link details for: ' + links[0]['url']
response = instamojo.link_detail(links[0]['slug'])
print response
for key in response['link']:
	try:
		print key + ': ' + response['link'][key]
	# Some values in the map are not strings eg. Bool (which throw an exception when printing)
	except Exception:
		continue

# Creating a new link
print '\nCreating a new link:'
response = instamojo.link_create('TestLink' + str(random.randint(2, 9999)), # Title
								 'Test Link Description', # Description
								 100, # Base Price
								 'INR', # Currency
								)
print 'Generated link: ' + response['link']['url']

# Getting list of payments
print '\nGetting list of payments:'
response = instamojo.payments_list()
payments = response['payments']
print 'You have ' + str(len(payments)) + ' payments!'
print payments