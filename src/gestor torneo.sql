use gestorTorneos;
-- gestor de torneos
-- Crear torneo
INSERT INTO torneos (nombre, numero_jugadores, numero_equipos) values();
-- comprobar si el nombre del torneo ya existe en la bd

-- iniciar torneo
Select nombre from torneos where numbTorneo = id
-- num torneo seria el numero que seleccionas
-- cuando se cree el calendario hay que poner la variable valido que esta en falso a true


-- modificar un equipo
UPDATE equipos set nombre = nuevonombre where nombre =  nombreseleccionado;
-- para modificar el nombre  comprobar que se cambie el nombre donde el nombre antiguo es el que seleccionaron y nombre nuevo es el que ponen debajo
UPDATE equipos set email = nuevoemail where nombre = nombreseleccionado;
-- en el email pasa igual el nombre seleccionado es el nombre del equipo que se selecciona al principio

-- modificar nombre y email
update equipos set nombre = nuevonombre, email = nuevoemail


-- eliminar un equipo
delete from equipos where nombre = nombreseleccionado;

-- en datos del jugador mostrar en la lista todos los jugadores del equipo seleccionado arriba
select nombre from jugadores where equipo = (select id from equipos where neombre = nombreseleccionado);
select * from jugadores where equipo = (select id from equipos where neombre = nombreseleccionado);

-- cuando seleccione un jugador que ya mustre la informacion de ese jugador
-- nombre
select nombre from jugadores where nombre = nombre_seleccionado;
-- fecha de nacimiento
select fecha_nacimiento from jugadores where nombre = nombre_seleccionado;
-- 1er apellido
select apellido1 from jugadores where nombre = nombre_seleccionado;
-- 2do apellido
select apellido2 from jugadores where nombre = nombre_seleccionado;
-- dni
select dni from jugadores where nombre = nombre_seleccionado;
-- seleccionar si a delegado si delegado es true
-- no tendria que devolver al delegado solo seleccionar el si asi que el select no estaria bien pero solo se podria hacer en java i si se mosifica cambiar al variable delegado y lo mismo para el arbitro
select delegado from jugadores where nombre = nombre_seleccionado and delegado is true;
select 


-- editar jugador
-- editar nombre
UPDATE jugadores set nombre = nuevonombre where nombre =  nombreseleccionado;
-- editar fecha de nacimiento y comprobar que tiene mas de 18 años
UPDATE jugadores set fecha_nacimiento = nuevofecha_nacimiento where nombre =  nombreseleccionado and yeardiff(nuevofecha_nacimiento, curdate())>18;
-- 
update jugadores set apellido1= nuevoapellido1, apellido2= nuevoapellido2, dni= nuevodni where nombre =  nombreseleccionado;
select delegado from jugadores where nombre = nombre_seleccionado and delegado is true;
select arbitro from jugadores where nombre = nombre_seleccionado and arbitro is true;

-- eliminar jugador
delete from jugadores where nombre = nombre_seleccionado;

-- ver calificacion
-- seleccionas un torneo y muestra sus equ`pos organizados por la puntuacion
select nombre , puntos from equipos where torneo =(select id from torneo where nombre = nombreseleccionado) order by puntos desc;