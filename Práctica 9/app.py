from flask import Flask, render_template, request, redirect, url_for, flash
from flask_mysqldb import MySQL

app = Flask(__name__)

app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = '' 
app.config['MYSQL_DB'] = 'dbflask'
app.config['SECRET_KEY'] = '12345'

mysql = MySQL(app)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/registro', methods=['GET', 'POST'])
def registro():
    if request.method == 'POST':
        rfc = request.form.get('rfc')
        nombre = request.form.get('nombre')
        cedula = request.form.get('cedula')
        correo = request.form.get('correo')
        contraseña = request.form.get('contraseña')
        rol = request.form.get('rol')

        cursor = mysql.connection.cursor()
        try:
            cursor.execute(
                "INSERT INTO tbMedicos (rfc, nombre, cedula, correo, contraseña, rol) VALUES (%s, %s, %s, %s, %s, %s)",
                (rfc, nombre, cedula, correo, contraseña, rol)
            )
            mysql.connection.commit()
            flash('Registro exitoso. Los datos han sido guardados correctamente.', 'success')
        except Exception as e:
            flash(f'Error al registrar los datos: {str(e)}', 'danger')
        finally:
            cursor.close()

        return redirect(url_for('consultar_medicos'))

    return render_template('registro.html')

@app.route('/consultar_medicos')
def consultar_medicos():
    cursor = mysql.connection.cursor()
    cursor.execute("SELECT * FROM tbMedicos")
    medicos = cursor.fetchall()
    cursor.close()
    return render_template('consultar_medicos.html', medicos=medicos)

@app.route('/editar_medico/<int:id>', methods=['GET', 'POST'])
def editar_medico(id):
    
    pass

@app.route('/ocultar_medico/<int:id>')
def ocultar_medico(id):
   
    pass

@app.errorhandler(404)
def paginano(e):
    return 'PÁGINA NO ENCONTRADA', 404

if __name__ == '__main__':
    app.run(port=3000, debug=True)
