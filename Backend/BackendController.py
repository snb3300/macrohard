"""
Copyright of Macrohard 
"""

from flask import Flask, request
from flask.ext.sqlalchemy import SQLAlchemy
from flask_restful import Resource, Api, reqparse
from sqlalchemy.exc import InvalidRequestError, IntegrityError
from sqlite3 import dbapi2 as sqlite3
import logging, sqlite3

# Log message formatting
logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.DEBUG)

# Main flask app
backendControllerAPP = Flask(__name__)
backendControllerAPP.config.from_pyfile(filename='BackendConfiguration.py', silent=False)
backendControllerAPI = Api(backendControllerAPP)

# SQLAlchemy object
db = SQLAlchemy(backendControllerAPP)


class UserInfo(db.Model):
    """
    Model class for user_info table in the database

    Describes the table schema in the database.
    Each Object is a row from the user_info table
    """
    # __tablename__ = 'user_info'
    # table columns
    email = db.Column(db.String(120), unique=True, primary_key=True)
    password = db.Column(db.String(128))
    name = db.Column(db.String(80))

    def __init__(self, email, password, name):
        self.password = password
        self.email = email
        self.name = name

    def __repr__(self):
        return '<User %r>' % self.name

class Register(Resource):
    """
    Flask API resource to process the user registration request
    """
    def post(self):
        """
        HTTP Post method for handling user registration

        Request must accompany with the user info parameters.
        Each request will create a UserInfo object and then the database
        object will commit data to the user_info table
        """
        error = None
        parser = reqparse.RequestParser()
        parser.add_argument('name', type=str, required=True)
        parser.add_argument('email', type=str, required=True)
        parser.add_argument('password', type=str, required=True)
        requestData = parser.parse_args()
        logging.debug(requestData)
        try:
            user = UserInfo(email=requestData['email'], password=requestData['password'], name=requestData['name'])
            db.session.add(user)
            db.session.commit()
            return {'Success': True, 'Name': requestData['name']}
        except IntegrityError as e:
            error =  requestData['email'] + " is already registered"
            logging.error(str(e))
        except InvalidRequestError as e:
            error = "Invalid Request"
            logging.error(str(e))
        except Exception as e:
            error = "Unknown Error"
            logging.error(str(e))
        return {'Success': False, 'Error': error}
# adds the path to call the registration resource
backendControllerAPI.add_resource(Register, '/register')

class Login(Resource):
    """
    Flask API resource to process the user login request
    """
    def get(self):
        """
        HTTP Get menthod to verify user credentials.

        Request must accompany with the parametes.
        """
        error = None
        parser = reqparse.RequestParser()
        parser.add_argument('username', type=str, required=True)
        parser.add_argument('password', type=str, required=True)
        requestData = parser.parse_args()
        logging.debug(requestData)
        try:
            user = UserInfo.query.filter_by(email=requestData['username']).filter_by(password=requestData['password']).first()
            logging.info(user)
            if user:
                return {'Success':True, 'Name': user.name}
            error = 'Invalid Credentials'
        except InvalidRequestError as e:
            error = str(e)
            logging.error(error)
        except Exception as e:
            error = str(e)
            logging.error(error)
        return {'Success': False, 'Error': error}
# adds the path to call the login resource
backendControllerAPI.add_resource(Login, '/login')

if __name__ == '__main__':
    backendControllerAPP.run(host='0.0.0.0', debug=True)