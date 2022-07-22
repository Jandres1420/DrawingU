var flag,color,user,erase,pintor,numeroPer,jugar,points;
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
        </button>
        <button
          type="button"
          class="btn btn-secondary btn-circle btn-xl"
          onClick={function () {
            color = "darkgray";
          }}
        >
        </button>
        <button
          type="button"
          class="btn btn-success btn-circle btn-xl"
          onClick={function () {
            color = "lawngreen";
          }}
        >
        </button>
        <button
          type="button"
          class="btn btn-danger btn-circle btn-xl"
          onClick={function () {
            color = "red";
          }}
        >
        </button>
        <button
          type="button"
          class="btn btn-warning btn-circle btn-xl"
          onClick={function () {
            color = "yellow";
          }}
        >
        </button>
        <button
          type="button"
          class="btn btn-light btn-circle btn-xl"
          onClick={function () {
            color = "snow";
          }}
        >
        </button>
        <button
          type="button"
          class="btn btn-dark btn-circle btn-xl"
          onClick={function () {
            color = "black";
          }}
        >
        </button>
        <button
          type="button"
          class="btn btn-dark"
          onClick={function () {
            if(pintor){
              flag = true;
            }
          }}
        >
          Borrar Tablero
        </button>
        <hr />
        <h2> Palabra a dibujar : </h2>
        <div id="postwords"></div>
     
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
      this.drawPoint(obj.x, obj.y, obj.color,obj.erase);
    });
    this.myp5 = null;
    this.state = { loadingState: "Loading Canvas ..." ,
                  pintor : "No sabemos",
                  puntaje : 0
                };
    // Es el cambio echo en el tablero
    let wsreference = this.comunicationWS;
    this.sketch = function (p) {
      let x = 100;
      let y = 100;
      p.setup = function () {
        p.createCanvas(700, 410);
      };
      p.draw = function () {
        if(jugar){
          if (p.mouseIsPressed === true && pintor) {
          erase = false;
          p.fill(color);
          p.ellipse(p.mouseX, p.mouseY, 10, 10);
          wsreference.send(p.mouseX, p.mouseY, color,erase);
         }
          if (p.mouseIsPressed === false) {
            p.fill(255, 255, 255);
          }
          if (flag) {
            p.clear();
            erase = true;
            wsreference.send(p.mouseX, p.mouseY, color, erase);
            flag = false;
          }
        }
        
      };
    };
  }
  drawPoint(x, y, color,erase) {
    console.log("x " , x);
    console.log("y ", y);
    console.log("color ", color);
    console.log("erase ", erase);
    this.myp5.fill(color);
    this.myp5.ellipse(x, y, 10, 10);
    console.log("El booleano es : "+erase);
    if(erase == "true"){
      console.log("ENTRA Y BORRAS " + erase);
      this.myp5.clear();
      erase = false;
    }
  }

  componentDidMount() {
    this.canPlay();
    this.numberOfPeople();
    this.numberOfPoints();
    this.myp5 = new p5(this.sketch, "container");
    this.setState({ loadingState: "Canvas Loaded" });
    this.getStatus(user);
    color = "black";
    flag = false;
    erase = false;
    this.timerID = setInterval(() => this.checkWord(), 25000); //Timer que da la palabra a dibujar 
    this.settingUserToChat();
    
  }
  checkWord() {
    if(pintor){
      let file = "/getWord";
      fetch(file, { method: "GET" })
        .then((x) => x.json())
        .then(
          (y) => (document.getElementById("postwords").innerHTML = y.getWord)
        );
    }
  }
  async numberOfPeople(){
    let file = "/numeroPersonas?key=" +""+localStorage.getItem("key")+"";
    numeroPer = await fetch(file, { method: "GET" })
      .then((x) => x.json()) 
    console.log("numero per" + numeroPer);
    
  }
  async numberOfPoints(){
    console.log("punticos");
    let file = "/gettingPoints?name=" + "" + user + "";
    points = await fetch(file, { method: "GET" })
      .then((x) => x.json())
    console.log("Estos son los puntos del usuario ",points);
    this.setState({ pintor: points });
    this.timerID = setInterval(() => this.numberOfPoints(),15000);
  }
  async canPlay(){
    let file = "/numeroPersonasBool?key=" + "" + localStorage.getItem("key") + "";
    jugar = await fetch(file, { method: "GET" })
      .then((x) => x.json())
    if(jugar){
      alert("El numero de personas actual es suficiente para jugar")
      this.timerID = setInterval(() => this.canPlay(), 10000000000);
    }else{
      alert("El numero de personas no es suficiente para jugar")
      this.timerID= setInterval(() => this.canPlay(), 10000);
    }
  }

  settingUserToChat(){
    document.getElementById("name").value = localStorage.getItem("user"); // Pone al input el usuario actual registrado
    var pagebutton = document.getElementById("botonusuario");
    pagebutton.click();
    
  }
  async getStatus(user) {
    let file = "/game?pintor=" + "'" + user + "'";

    pintor = await fetch(file, { method: "GET" })
      .then((x) => x.json())
    if(pintor){
      this.setState({pintor:"Eres pintor"});
    }else{
      this.setState({pintor:"No eres pintor"});
    }
    console.log("Asignando variable " + pintor);
  }
  render() {
    return (
      <div>
        <h5>{this.state.pintor}</h5>
        <h6> numero de puntos del jugador</h6>
        <h7> {this.state.puntaje} </h7>
      </div>
    );
  }
}
// Retorna la url del servicio. Es una función de configuración.
function BBServiceURL() {
  var host = window.location.host;
  var url = "wss://" + host + "/bbService";
  if (host.startsWith("localhost")) {
    url = "ws://localhost:8080/bbService";
  }
  console.log("URL Calculada: " + url);
  return url;
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
  send(x, y, color,erase) {
    let msg =
      '{ "x": ' + x + ', "y": ' + y + ', "color": "' + color + '", "erase": "' + erase + '"'+ "}";
    console.log("sending: ", msg);
    console.log("color ", color);
    console.log("Borar ", erase)
    //enviar puntos por socket
    this.wsocket.send(msg);
  }
}

ReactDOM.render(
  <Editor name={localStorage.getItem("user")} />,
  document.getElementById("root")
);
