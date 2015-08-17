from flask import Flask, request
from flask.ext.sqlalchemy import SQLAlchemy
from flask_restful import Resource, Api, reqparse
from sqlite3 import dbapi2 as sqlite3
import logging, sqlite3

logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.DEBUG)

backendControllerAPP = Flask(__name__)
backendControllerAPP.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://testbot:testbot@localhost/macrohard'
backendControllerAPI = Api(backendControllerAPP)
db = SQLAlchemy(backendControllerAPP)


class User_Info(db.Model):
    user_id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), unique=True)
    password = db.Column(db.String(80))
    email = db.Column(db.String(120), unique=True)
    firstname = db.Column(db.String(80))
    lastname = db.Column(db.String(80))

    def __init__(self, username, password, email, firstname, lastname):
        self.username = username
        self.password = password
        self.email = email
        self.firstname = firstname
        self.lastname = lastname

    def __repr__(self):
        return '<User %r>' % self.username

class Login(Resource):
    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('username', type=str)
        parser.add_argument('password', type=str)
        requestData = parser.parse_args()
        user = User_Info.query.filter_by(username=requestData['username']).filter_by(password=requestData['password']).first()
        logging.info(user)
        if user:
            return {'Success':True, 'User_Id':user.user_id, 'First Name': user.firstname, 'Last Name': user.lastname}
        else:
            return {'Success': False}

backendControllerAPI.add_resource(Login, '/login')

if __name__ == '__main__':
    backendControllerAPP.run(debug=True)