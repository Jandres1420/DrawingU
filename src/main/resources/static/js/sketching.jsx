var flag;
var color;
class Editor extends React.Component {
  render() {
    return (
      <div>
        <h1>Hello, {this.props.name}</h1>
        <button type="button" class="btn btn-primary btn-circle btn-xl" onClick={function(){color = "blue"}} >Blue</button>
        <button type="button" class="btn btn-secondary btn-circle btn-xl" onClick={function(){color = "darkgray"}}>Gray</button>
        <button type="button" class="btn btn-success btn-circle btn-xl" onClick={function(){color = "lawngreen"}}>Green</button>
        <button type="button" class="btn btn-danger btn-circle btn-xl" onClick={function(){color = "red"}}>Red</button>
        <button type="button" class="btn btn-warning btn-circle btn-xl" onClick={function(){color = "yellow"}}>Yellow</button>
        <button type="button" class="btn btn-light btn-circle btn-xl" onClick={function(){color = "snow"}}>White</button>
        <button type="button" class="btn btn-dark btn-circle btn-xl" onClick={function(){color = "black"}}>Black</button>
        <button type="button" class="btn btn-dark" onClick={function(){flag = true}} >Borrar Tablero</button>
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
    this.comunicationWS =new WSBBChannel(BBServiceURL(),(msg) =>{
 			var obj = JSON.parse(msg);
 			console.log("On func call back ", msg);
 			this.drawPoint(obj.x, obj.y);
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
          wsreference.send(p.mouseX, p.mouseY);
        }
        if (p.mouseIsPressed === false) {
          p.fill(255, 255, 255);
        }
        if(flag){
          p.clear();
          flag = false;
        }
      };
    };
  }
  drawPoint(x, y){
    this.myp5.ellipse(x, y, 10, 10);
   }

  componentDidMount() {
    this.myp5 = new p5(this.sketch, "container");
    this.setState({ loadingState: "Canvas Loaded" });
    color = 'black'
    flag = false;
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
    var url = 'ws://localhost:8080/bbService';
    console.log("URL Calculada: " + url);
    return 'ws://localhost:8080/bbService';
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
        if (evt.data != "Connection established."){
            this.receivef(evt.data);
        }
    }
    onError(evt) {
        console.error("In onError", evt);
    }
    // Enviar puntos
    send(x, y) {
        let msg = '{ "x": ' + (x) + ', "y": ' + (y) +"}";
        console.log("sending: ", msg);
        //enviar puntos por socket
        this.wsocket.send(msg);
    }
}   

ReactDOM.render(<Editor name="Andrés" />, document.getElementById("root"));
