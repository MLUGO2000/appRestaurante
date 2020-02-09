<?PHP
$hostname_localhost ="localhost";
$database_localhost ="bd_restaurante";
$username_localhost ="manuel";
$password_localhost ="ml2000";

$usu_usuario=$_POST['usuario'];
$usu_password=$_POST['password'];

//$usu_usuario="manuellugo2000ml@gmail.com";
//$usu_password="ml2000";

		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		$sql="SELECT * FROM usuarios WHERE usu_usuario=? AND usu_password=?";
		$sentencia=$conexion->prepare($sql);
		$sentencia->bind_param('ss',$usu_usuario,$usu_password);
		$sentencia->execute();
		
		$resultado=$sentencia->get_result();
		
		if($fila=$resultado->fetch_assoc()){
			
		echo json_encode($fila,JSON_UNESCAPED_UNICODE);
		}
		$sentencia->close();
		$conexion->close();
		
		
?>