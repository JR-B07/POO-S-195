from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('home.html')

@app.route('/formulario', methods=['GET', 'POST'])
def formulario():
    if request.method == 'POST':
        nombre = request.form.get('nombre')
        edad = request.form.get('edad')
        return f'Nombre: {nombre}, Edad: {edad}'
    return render_template('formulario.html')

@app.route('/numeroalcuadrado/<int:numero>')
def numero_al_cuadrado(numero):
    return f'El número {numero} al cuadrado es {numero * numero}'

@app.errorhandler(404)
def paginano(e):
    return 'PÁGINA NO ENCONTRADA'

if __name__ == '__main__':
    app.run(port=3000, debug=True)
