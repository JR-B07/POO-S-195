from flask import Flask, request

app = Flask(__name__)

#RUTA SIMPLE
@app.route('/')
def principal():
    return 'hola mundo Flask'

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
    