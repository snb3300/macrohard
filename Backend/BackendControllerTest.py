import requests

def testLogin():
	url = 'http://localhost:5000/login'
	data = {'username':'guest@example.com', 'password':'guest'}
	r = requests.get(url, params=data)
	print r.json()

def testRegistration():
	url = 'http://localhost:5000/register'
	data = {'name':'Shridhar', 'email':'s@b.com', 'password':'test'}
	r = requests.post(url, params=data)
	print r.json()

if __name__ == "__main__":
	# testLogin()
	testRegistration()