from BackendController import db
from BackendController import UserInfo

db.create_all()
admin = UserInfo('admin@example.com', 'admin', 'Admin')
guest = UserInfo('guest@example.com', 'guest', 'Guest')

db.session.add(admin)
db.session.add(guest)
db.session.commit()