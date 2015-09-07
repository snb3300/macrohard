import requests

def testLogin():
	url = 'http://localhost:5000/login'
	data = {'username':'b@c.com', 'password':'bob'}
	r = requests.get(url, params=data)
	print r.json()

def testRegistration():
	url = 'http://localhost:5000/register'
	data = {'name':'Shridhar', 'email':'b@c.com', 'password':'bob'}
	r = requests.post(url, params=data)
	print r.json()

if __name__ == "__main__":
	testLogin()
	# testRegistration()