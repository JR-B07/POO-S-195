from flask import Flask, request,render_template
from flask_mysqldb import MySQL

app = Flask(__name__)
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = '12345'
app.config['MYSQL_DB'] = 'dbflask'
mysql = MySQL(app)


@app.route('/')
def index():
    return render_template('index.html')

@app.route('/guardarAlbum', methods=['POST'])
def guardarAlbum():
    if request.method == 'POST':
       Titulo= request.form['txtTitulo']
       Artista= request.form['txtArtista'] 
       Año= request.form['txtAnio']
       print(Titulo,Artista,Año)
       return 'Datos recibidos en el Server'

@app.errorhandler(404)
def paginanotfound(e):
    return 'Revisa tu sintaxis: No encontre nada'

if __name__ == '__main__':
    app.run(port=3000, debug=True)