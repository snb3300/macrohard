import requests

def testLogin():
	url = 'http://localhost:5000/login'
	data = {'username':'gue', 'password':'guest'}
	r = requests.get(url, params=data)
	print r.json()

if __name__ == "__main__":
	testLogin()