from flask import Flask, request, render_template
from flask_mysqldb import MySQL

app = Flask(__name__)
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = 'dbflask'
mysql = MySQL(app)

@app.route('/')
def index():
    try:
        cursor = mysql.connection.cursor()
        cursor.execute('SELECT * FROM albums')
        consultaA = cursor.fetchall()
        print(consultaA)
        return render_template('index.html', albums=consultaA)
    except Exception as e:
        print(e)
        return "Error al realizar la consulta", 500

@app.route('/guardarAlbum', methods=['POST'])
def guardarAlbum():
    if request.method == 'POST':
        Titulo = request.form['txtTitulo']
        Artista = request.form['txtArtista']
        Año = request.form['txtAnio']
        try:
            cursor = mysql.connection.cursor()
            cursor.execute("INSERT INTO albums (titulo, artista, anio) VALUES (%s, %s, %s)", (Titulo, Artista, Año))
            mysql.connection.commit()
            return 'Datos recibidos en el servidor y almacenados en la base de datos'
        except Exception as e:
            print(e)
            return "Error al guardar en la base de datos", 500

@app.errorhandler(404)
def paginanotfound(e):
    return 'Revisa tu sintaxis: No encontré nada', 404

if __name__ == '__main__':
    app.run(port=3000, debug=True)
