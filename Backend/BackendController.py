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


class UserInfo(db.Model):
    email = db.Column(db.String(120), unique=True, primary_key=True)
    password = db.Column(db.String(80))
    name = db.Column(db.String(80))

    def __init__(self, email, password, name):
        self.password = password
        self.email = email
        self.name = name

    def __repr__(self):
        return '<User %r>' % self.name

class Login(Resource):
    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('username', type=str)
        parser.add_argument('password', type=str)
        requestData = parser.parse_args()
        logging.debug(requestData)
        try:
            user = User_Info.query.filter_by(username=requestData['username']).filter_by(password=requestData['password']).first()
            logging.info(user)
            if user:
                return {'Success':True, 'Name': user.name}
        except InvalidRequestError as e:
            logging.error(str(e))
        except Exception as e:
            logging.error(str(e))
        finally:
            return {'Success': False}

backendControllerAPI.add_resource(Login, '/login')

if __name__ == '__main__':
    backendControllerAPP.run(host='0.0.0.0', debug=True)