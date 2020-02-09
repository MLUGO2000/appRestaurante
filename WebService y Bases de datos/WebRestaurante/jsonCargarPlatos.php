<?PHP
$hostname_localhost ="localhost";
$database_localhost ="bd_restaurante";
$username_localhost ="manuel";
$password_localhost ="ml2000";
$json=array();

		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		$consulta= "select * from platos";
		
		$resultado=mysqli_query($conexion,$consulta);

			while($registro=mysqli_fetch_array($resultado)){
				$result["id"]=$registro['ID'];
				$result["nombre"]=$registro['NOMBRE'];
				$result["precio"]=$registro['PRECIO'];
				$result["descripcion"]=$registro['DESCRIPCION'];
				$result["imagen"]=$registro['IMAGEN'];
			
				$json['plato'][]=$result;
				//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
			}
			mysqli_close($conexion);
			echo json_encode($json);

?>