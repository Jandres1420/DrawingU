var flag;
var color;
var user;
class Editor extends React.Component {
  render() {
    return (
      <div>
        <h1>Hello, {this.props.name}</h1>
        <script> function(){(user = this.props.name)}</script>
        <button
          type="button"
          class="btn btn-primary btn-circle btn-xl"
          onClick={function () {
            color = "blue";
          }}
        >
          Blue
        </button>
        <button
          type="button"
          class="btn btn-secondary btn-circle btn-xl"
          onClick={function () {
            color = "darkgray";
          }}
        >
          Gray
        </button>
        <button
          type="button"
          class="btn btn-success btn-circle btn-xl"
          onClick={function () {
            color = "lawngreen";
          }}
        >
          Green
        </button>
        <button
          type="button"
          class="btn btn-danger btn-circle btn-xl"
          onClick={function () {
            color = "red";
          }}
        >
          Red
        </button>
        <button
          type="button"
          class="btn btn-warning btn-circle btn-xl"
          onClick={function () {
            color = "yellow";
          }}
        >
          Yellow
        </button>
        <button
          type="button"
          class="btn btn-light btn-circle btn-xl"
          onClick={function () {
            color = "snow";
          }}
        >
          White
        </button>
        <button
          type="button"
          class="btn btn-dark btn-circle btn-xl"
          onClick={function () {
            color = "black";
          }}
        >
          Black
        </button>
        <button
          type="button"
          class="btn btn-dark"
          onClick={function () {
            flag = true;
          }}
        >
          Borrar Tablero
        </button>
        <hr />
        <h2> Palabra a dibujar : </h2>
        <div id="postwords"></div>
        <div id="pintor">
          <h3> Buenas aca va a estar si eres pintor </h3>
          <script id="estado"></script>
        </div>
        <hr />
        <div id="toolstatus"></div>
        <hr />
        <div id="container"></div>
        <BBCanvas />
        <hr />
        <div id="info"></div>
      </div>
    );
  }
}

class BBCanvas extends React.Component {
  constructor(props) {
    super(props);
    this.comunicationWS = new WSBBChannel(BBServiceURL(), (msg) => {
      var obj = JSON.parse(msg);
      console.log("On func call back ", msg);
      this.drawPoint(obj.x, obj.y, obj.color);
    });
    this.myp5 = null;
    this.state = { loadingState: "Loading Canvas ..." };
    // Es el cambio echo en el tablero
    let wsreference = this.comunicationWS;
    this.sketch = function (p) {
      let x = 100;
      let y = 100;
      p.setup = function () {
        p.createCanvas(700, 410);
      };
      p.draw = function () {
        if (p.mouseIsPressed === true) {
          p.fill(color);
          p.ellipse(p.mouseX, p.mouseY, 10, 10);
          wsreference.send(p.mouseX, p.mouseY, color);
        }
        if (p.mouseIsPressed === false) {
          p.fill(255, 255, 255);
        }
        if (flag) {
          p.clear();
          flag = false;
        }
      };
    };
  }
  drawPoint(x, y, color) {
    this.myp5.fill(color);
    this.myp5.ellipse(x, y, 10, 10);
  }

  componentDidMount() {
    this.myp5 = new p5(this.sketch, "container");
    this.setState({ loadingState: "Canvas Loaded" });
    color = "black";
    flag = false;
    console.log(" Este es el nombre por local " + user);
    this.timerID = setInterval(() => this.checkWord(), 25000);
    this.getStatus(user);
  }
  checkWord() {
    console.log("Estan oprimiendo el boton");
    let file = "/getWord";
    console.log("file " + file);
    fetch(file, { method: "GET" })
      .then((x) => x.json())
      .then(
        (y) => (document.getElementById("postwords").innerHTML = y.getWord)
      );
  }

  getStatus(user) {
    console.log("ENTRO AL ESTDO");
    let file = "/game?pintor=" + "'" + user + "'";
    console.log("file " + file);
    fetch(file, { method: "GET" })
      .then((x) => x.text())
      .then((y) => (document.getElementById("estado").innerHTML = y));
  }
  render() {
    return (
      <div>
        <h4>Drawing status: {this.state.loadingState}</h4>
      </div>
    );
  }
}
// Retorna la url del servicio. Es una función de configuración.
function BBServiceURL() {
  var host = window.location.host;
  var url = "wss://" + host + "/bbService";
  console.log("URL Calculada: " + url);
  return "ws://localhost:8080/bbService";
}

class WSBBChannel {
  constructor(URL, callback) {
    this.URL = URL;
    this.wsocket = new WebSocket(URL);
    this.wsocket.onopen = (evt) => this.onOpen(evt);
    this.wsocket.onmessage = (evt) => this.onMessage(evt);
    this.wsocket.onerror = (evt) => this.onError(evt);
    this.receivef = callback;
  }
  onOpen(evt) {
    console.log("In onOpen", evt);
  }
  onMessage(evt) {
    console.log("In onMessage", evt);
    // Este if permite que el primer mensaje del servidor no se tenga en cuenta.
    // El primer mensaje solo confirma que se estableció la conexión.
    // De ahí en adelante intercambiaremos solo puntos(x,y) con el servidor
    if (evt.data != "Connection established.") {
      this.receivef(evt.data);
    }
  }
  onError(evt) {
    console.error("In onError", evt);
  }
  // Enviar puntos
  send(x, y, color) {
    let msg =
      '{ "x": ' + x + ', "y": ' + y + ', "color": "' + color + '"' + "}";
    console.log("sending: ", msg);
    console.log("color ", color);
    //enviar puntos por socket
    this.wsocket.send(msg);
  }
}
class StatusComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      status: "",
    };
  }
  componentDidMount() {
    this.timerID = setInterval(() => this.checkStatus(), 5000);
  }
  checkStatus() {
    console.log("Estan oprimiendo el boton");
    var getWord = document.getElementById("name").value;
    localStorage.setItem("user", name);
    let file = "/getWord";
    console.log("file " + file);
    fetch(file, { method: "GET" })
      .then((x) => x.text())
      .then((y) => (document.getElementById("postwords").innerHTML = y));
  }
  render() {
    const { error, isLoaded, status } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <div>
          <h1>The server status is:</h1>
          <p>{status}</p>
        </div>
      );
    }
  }
}

ReactDOM.render(
  <Editor name={localStorage.getItem("user")} />,
  document.getElementById("root")
);
