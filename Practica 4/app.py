from flask import Flask, request,jsonify
from flask_mysqldb import MySQL

app = Flask(__name__)
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = '12345'
app.config['MYSQL_DB'] = 'dbflask'
mysql = MySQL(app)


#RUTA SIMPLE
@app.route('/')
def principal():
    return 'hola mundo Flask'

#RUTA SIMPLE
@app.route('/PruebaConexion')
def PruebaConexion():
    try:
        cursor= mysql.connection.cursor()
        cursor.execute('SELECT 1')
        datos = cursor.fetchone()
        return jsonify({'status': 'Conexion Exitosa', 'data':datos})
    except Exception as ex:
        return jsonify({'status': 'Error de Conexion', 'mensaje': str(ex)})
    
#RUTA DOBLE
@app.route('/usuario')
@app.route('/saludar')
def saludos():
    return 'hola José Ricardo'

#RUTAS CON PARÁMETROS
@app.route('/hi/<nombre>')
def hi(nombre):
    return 'hola' + nombre +'!!!'

#DEFINICION DE METODOS DE TRABAJO
@app.route('/formulario', methods=['GET','POST']) 
def formulario():
    if request.method == 'GET':
        return 'No es seguro enviar password por GET'
    elif request.method == 'POST':
        return 'POST si es seguro para passwords'
    


#Manejo de excepciones para rutas     
@app.errorhandler(404)
def paginanotfound(e):
    return 'Revisa tu sintaxis: No encontre nada'


if __name__ == '__main__':
    app.run(port=3000, debug=True)
    