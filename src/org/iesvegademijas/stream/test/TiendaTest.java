package org.iesvegademijas.stream.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import org.iesvegademijas.hibernate.Fabricante;
import org.iesvegademijas.hibernate.FabricanteHome;
import org.iesvegademijas.hibernate.Producto;
import org.iesvegademijas.hibernate.ProductoHome;
import org.junit.jupiter.api.Test;


class TiendaTest {
	
	@Test
	void testSkeletonFrabricante() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
		
			
			//TODO STREAMS
//el profe me ha dicho de no hacerlo
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	

	@Test
	void testSkeletonProducto() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
//el profe me ha dicho de no hacerlo
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	@Test
	void testAllFabricante() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
			assertEquals(9,listFab.size());
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	@Test
	void testAllProducto() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			assertEquals(11,listProd.size());
		
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		

	
	}
	
	/**
	 * 1. Lista los nombres y los precios de todos los productos de la tabla producto
	 */
	@Test
	void test1() {
	
		ProductoHome prodHome = new ProductoHome();
		
		try {
			prodHome.beginTransaction();
			
			List<Producto> listProd = prodHome.findAll();
			//TODO STREAMS
			listProd.stream()
					//obtener nombre y precio
					.map(p ->"Nombre: "+ p.getNombre()+",Precio: "+p.getPrecio())
					//imprimir
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	
	}
	
	
	/**
	 * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
	 */
	@Test
	void test2() {
		
		ProductoHome prodHome = new ProductoHome();
		
		try {
			prodHome.beginTransaction();			
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			listProd.stream()
					//lo mismo que arriba pero con dolores precio*1.05
					.map(p ->"Nombre: "+ p.getNombre()+",Precio: "+ BigDecimal.valueOf(p.getPrecio()*1.05).setScale(2, RoundingMode.HALF_UP)+" dolares")
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
	 */
	@Test
	void test3() {
		
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			listProd.stream()
					//lo mismo que uno pero con toUpperCase
					.map(p ->"Nombre: "+ p.getNombre().toUpperCase()+",Precio: "+p.getPrecio())
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
	 */
	@Test
	void test4() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS
			listFab.stream()
					//nombre y el nombre+uppercase+substring
					.map(p ->"Nombre: "+ p.getNombre()+", "+p.getNombre().toUpperCase().substring(0,2))
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 5. Lista el código de los fabricantes que tienen productos.
	 */
	@Test
	void test5() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS
			listFab.stream()
					//filter si producto vacio
					.filter(f->!f.getProductos().isEmpty())
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
	 */
	@Test
	void test6() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();

			//TODO STREAMS
			listFab.stream()
					//ordenar por nombre
					.sorted(comparing(Fabricante::getNombre)
							//reverse
							.reversed())
					.forEach(System.out::println);

			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 7. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
	 */
	@Test
	void test7() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			listProd.stream()
					//ordenar por nombre
					.sorted(comparing(Producto::getNombre)
							//despues por precio
							.thenComparing(comparing(Producto::getPrecio)
									//precio al reverse
									.reversed()))
					.map(p ->"Nombre: "+ p.getNombre()+",Precio: "+p.getPrecio())
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 8. Devuelve una lista con los 5 primeros fabricantes.
	 */
	@Test
	void test8() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS
			listFab.stream()
					//limitar en 5
					.limit(5)
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
	 */
	@Test
	void test9() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS
			listFab.stream()
					//saltar 3, empezar por la 4
					.skip(3)
					//obtener los primeros 2
					.limit(2)
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 10. Lista el nombre y el precio del producto más barato
	 */
	@Test
	void test10() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();


			//TODO STREAMS
			listProd.stream()
					//obtener el mas barato
					.min(comparingDouble(Producto::getPrecio))
					//formatear la salida
					.map(p -> "Nombre: "+p.getNombre()+", Precio: "+p.getPrecio())
					.ifPresent(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 11. Lista el nombre y el precio del producto más caro
	 */
	@Test
	void test11() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			listProd.stream()
					//obtener el mas caro
					.max(comparingDouble(Producto::getPrecio))
					//formatear la salida
					.map(p -> "Nombre: "+p.getNombre()+", Precio: "+p.getPrecio())
					.ifPresent(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
	 * 
	 */
	@Test
	void test12() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			listProd.stream()
					//filtrar cod_fabricante==2
					.filter(p->p.getFabricante().getCodigo()==2)
					//formatear la salida
					.map(p -> "Nombre: "+p.getNombre())
					.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
	 */
	@Test
	void test13() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			listProd.stream()
					//filtrar precio <=120
					.filter(p->p.getPrecio()<=120)
					//formatear salida
					.map(p -> "Nombre: "+p.getNombre())
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 14. Lista los productos que tienen un precio mayor o igual a 400€.
	 */
	@Test
	void test14() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			listProd.stream()
					//filtrar precio >=400
					.filter(p->p.getPrecio()>=400)
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 15. Lista todos los productos que tengan un precio entre 80€ y 300€. 
	 */
	@Test
	void test15() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();	
			
			//TODO STREAMS
			listProd.stream()
					//filtrar producto con precio entre 80 y 300, incluidos
					.filter(p->p.getPrecio()>=80 && p.getPrecio()<=300)
					.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
	 */
	@Test
	void test16() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			listProd.stream()
					//filtrar productos con precio >=200 y codigoFabricante==6
					.filter(p->p.getPrecio()>=200 && p.getFabricante().getCodigo()==6)
					.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
	 */
	@Test
	void test17() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			
			//TODO STREAMS
			//preparar un set con los codigos
			Set<Integer> codigos=new HashSet<>();
			codigos.add(1);
			codigos.add(3);
			codigos.add(5);
			listProd.stream()
					//filtrar si el codigo esta en el set
					.filter(p->codigos.contains(p.getFabricante().getCodigo()))
					.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 18. Lista el nombre y el precio de los productos en céntimos.
	 */
	@Test
	void test18() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			
			//TODO STREAMS
			listProd.stream()
					//formatear la salida con el precio en centimo (*100)
					.map(p ->"Nombre: "+ p.getNombre()+",Precio: "+ p.getPrecio()*100+" céntimos")
					.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	
	/**
	 * 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S
	 */
	@Test
	void test19() {
	
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS
			listFab.stream()
					//filtrar nombre charAt0==S
					.filter(f->f.getNombre().charAt(0)=='S')
					//formatear
					.map(f->"Nombre: "+f.getNombre())
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
	 */
	@Test
	void test20() {
	
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			listProd.stream()
					//filter contains
					.filter(p->p.getNombre().contains("Portátil"))
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
	 */
	@Test
	void test21() {
	
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			listProd.stream()
					//filter contain y precio<215
					.filter(p->p.getNombre().contains("Monitor") && p.getPrecio()<215)
					.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
	 */
	@Test
	void test22() {
		
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			listProd.stream()
					//filter precio
					.filter(p->p.getPrecio()>=180)
					//ordenar con precio invertido
					.sorted(comparing(Producto::getPrecio)
							.reversed()
							//despues ordenar con nombre
							.thenComparing(Producto::getNombre))
					//formatear la salida
					.map(p ->"Nombre: "+ p.getNombre()+",Precio: "+p.getPrecio())
					.forEach(System.out::println);
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos. 
	 * Ordene el resultado por el nombre del fabricante, por orden alfabético.
	 */
	@Test
	void test23() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			listProd.stream()
					//ordenar
					.sorted(comparing(p -> p.getFabricante().getNombre()))
					//formatear
					.map(p ->"Nombre: "+ p.getNombre()+", Precio: "+p.getPrecio()+", Fabricante:"+p.getFabricante().getNombre())
					.forEach(System.out::println);
			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
	 */
	@Test
	void test24() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS

			listProd.stream()
					//obtener el mas caro
					.max(comparingDouble(Producto::getPrecio))
					//formatear
					.map(p ->"Nombre: "+ p.getNombre()+", Precio: "+p.getPrecio()+", Fabricante:"+p.getFabricante().getNombre())
					.ifPresent(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
	 */
	@Test
	void test25() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			listProd.stream()
					//filtrar fabricante crucial y precio>200
					.filter(p->p.getFabricante().getNombre().equals("Crucial")&&p.getPrecio()>200)
					.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
	 */
	@Test
	void test26() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			//Igual que la 17 pero esa vez con el nombre de fabricante
			Set<String> codigos=new HashSet<>();
			codigos.add("Asus");
			codigos.add("Hewlett-Packard");
			codigos.add("Seagate");

			listProd.stream()
					.filter(p->codigos.contains(p.getFabricante().getNombre()))
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
	 * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
	 * La salida debe quedar tabulada como sigue:

Producto                Precio             Fabricante
-----------------------------------------------------
GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
Portátil Yoga 520      |452.79            |Lenovo
Portátil Ideapd 320    |359.64000000000004|Lenovo
Monitor 27 LED Full HD |199.25190000000003|Asus

	 */		
	@Test
	void test27() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			//filtrar y ordenar que valen pa todos
			listProd=listProd.stream()
					.filter(p->p.getPrecio()>=180)
					.sorted(comparing(Producto::getPrecio).reversed()
							.thenComparing(Producto::getNombre))
					.collect(toList());
			//obteniendo la longitud maxima para cada campo
//la longitud maxima no incluye la primera fila("Producto" "Precio" "Fabricante")
// porque en ese caso no es necesario
// si se quiere añadir hay que obtener una lista y añadirlos etc. y son lineas de codigos mas
			//lenght nombre
			int ln=listProd.stream()
					.map(p->p.getNombre().length())
					.max(Integer::compareTo)
					.orElse(0);
			//lenght producto
			int lp=listProd.stream()
					.map(p ->(p.getPrecio()+"").length() )
					.max(Integer::compareTo)
					.orElse(0);
			//lenght fabricante
			int lf=listProd.stream()
					.map(p ->p.getFabricante().getNombre().length())
					.max(Integer::compareTo)
					.orElse(0);
			//imprimir la primera fila
			System.out.println(String.format("%1$-"+(ln+1)+"s","Producto")
					+String.format("%1$-"+(lp+1)+"s","Precio")
					+String.format("%1$-"+(lf)+"s","Fabricante"));
			//imprimir los "-", +2 por 2"|"
			System.out.println("-".repeat(ln+lp+lf+2));
			//imprimir los productos
			listProd.stream()
					.map(p->String.format("%1$-"+ln+"s",p.getNombre())+"|"
							+String.format("%1$-"+lp+"s",p.getPrecio())+"|"
							+String.format("%1$-"+lf+"s",p.getFabricante().getNombre()))
					.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos.
	 * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados. 
	 * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
	 * La salida debe queda como sigue:
Fabricante: Asus

            	Productos:
            	Monitor 27 LED Full HD
            	Monitor 24 LED Full HD

Fabricante: Lenovo

            	Productos:
            	Portátil Ideapd 320
            	Portátil Yoga 520

Fabricante: Hewlett-Packard

            	Productos:
            	Impresora HP Deskjet 3720
            	Impresora HP Laserjet Pro M26nw

Fabricante: Samsung

            	Productos:
            	Disco SSD 1 TB

Fabricante: Seagate

            	Productos:
            	Disco duro SATA3 1TB

Fabricante: Crucial

            	Productos:
            	GeForce GTX 1080 Xtreme
            	Memoria RAM DDR4 8GB

Fabricante: Gigabyte

            	Productos:
            	GeForce GTX 1050Ti

Fabricante: Huawei

            	Productos:


Fabricante: Xiaomi

            	Productos:

	 */
	@Test
	void test28() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS
			listFab.stream()
					//formatear la salida(sobretodo los \n y \t)
					.map(f -> "Fabricante: "+f.getNombre()+"\n\n\tProductos:\n"+
							//un segundo stream para los productos
							f.getProductos().stream()
									//formatear la salida de producto
									.map(p -> "\t"+p.getNombre()+"\n")
									//para que no salta como lista, osea sin "[],"
									.collect(joining()))
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
	 */
	@Test
	void test29() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS
			listFab.stream()
					//filtrar listaProducto vacia
					.filter(f-> f.getProductos().isEmpty())
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
	 */
	@Test
	void test30() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			//contar (lo mismo que size sin stream)
			System.out.println(listProd.stream().count());
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}

	
	/**
	 * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
	 */
	@Test
	void test31() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			System.out.println(listProd.stream()
					//obtener los fabricantes
					.map(Producto::getFabricante)
					//qutiar repetido
					.distinct()
					//contar
					.count());
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 32. Calcula la media del precio de todos los productos
	 */
	@Test
	void test32() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			//convertir el resultado en bigddecimal para redondear
			System.out.println(BigDecimal.valueOf(
					listProd.stream()
							//obtener la media
							.collect(averagingDouble(Producto::getPrecio)))
					//redondear a dos decimales
					.setScale(2,RoundingMode.HALF_UP));
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
	 */
	@Test
	void test33() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			System.out.println(listProd.stream()
					//obtener el precio
					.map(Producto::getPrecio)
					//obtener el mas barato
					.min(Double::compareTo)
					//lo mismo que get pero si no hubiera nada devuelve 0.0, el get te va a decir de ponerlo en un ifPresent
					.orElse(0.0));
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 34. Calcula la suma de los precios de todos los productos.
	 */
	@Test
	void test34() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			System.out.println(listProd.stream()
					//mapear a double
					.mapToDouble(Producto::getPrecio)
					//suma
					.sum());
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 35. Calcula el número de productos que tiene el fabricante Asus.
	 */
	@Test
	void test35() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			System.out.println(listProd.stream()
					//obtener productos con fabricante asus
					.filter(p->p.getFabricante().getNombre().equals("Asus"))
					//contar
					.count());
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 36. Calcula la media del precio de todos los productos del fabricante Asus.
	 */
	@Test
	void test36() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			//imprimir y convertir el resultado en big decimal para redondear
			System.out.println(BigDecimal.valueOf(listProd.stream()
					//filtrar Asus
						.filter(p->p.getFabricante().getNombre().equals("Asus"))
					//calcular la media
						.collect(averagingDouble(Producto::getPrecio)))
					//redondear
					.setScale(2,RoundingMode.HALF_UP));
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	
	/**
	 * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial. 
	 *  Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 */
	@Test
	void test37() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			//TODO STREAMS
			//se ha comprobado que funcionace bien quitando el filter.
            Double[] res= listProd.stream()
					//filtrar crucial
					.filter(p->p.getFabricante().getNombre().equals("Crucial"))
					//mapear a double[] para que reduce nos devuelva un double[]
                    .map(p->new Double[]{p.getPrecio()})
					//con valor inicial para no obtener un optional el cuarto parametro seria el conteo que va sumando
                    .reduce(new Double[]{Double.MIN_VALUE,Double.MAX_VALUE,0.0,0.0},(d1, d2)->
							//obtener un double (los siguientes d1)
							new Double[]{
									//primer parametro max
									Math.max(d1[0], d2[0]),
									//min
									Math.min(d1[1], d2[0]),
									//media (ahora*conteo anterior) para obtener el total + el siguiente dividido conteo actual(anterior+1)
									(d1[2]*d1[3]+d2[0])/(d1[3]+1),
									//conteo
		                            d1[3]+1
							}
                    );
			//imprimir resultado
			System.out.println("Max: "+res[0]);
			System.out.println("Min: "+res[1]);
			System.out.println("Avg: "+res[2]);
			System.out.println("Count: "+res[3]);

            prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 38. Muestra el número total de productos que tiene cada uno de los fabricantes. 
	 * El listado también debe incluir los fabricantes que no tienen ningún producto. 
	 * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene. 
	 * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
	 * La salida debe queda como sigue:
	 
     Fabricante     #Productos
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
           Asus              2
         Lenovo              2
Hewlett-Packard              2
        Samsung              1
        Seagate              1
        Crucial              2
       Gigabyte              1
         Huawei              0
         Xiaomi              0

	 */
	@Test
	void test38() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			//obtener la longitud
			int lf=listFab.stream()
					.map(f ->f.getNombre().length())
					.max(Integer::compareTo)
					.orElse(0);
			//imprimir la primera fila
			System.out.println(String.format("%"+lf+"s","Fabricante")+
					String.format("%"+lf+"s","Producto"));
			//imprimir la segunda
			System.out.println("-*".repeat(lf*2));
			//imprimir los fabricantes y numero de producto
			listFab.stream()
					//ordenar por cantidad de producto
					.sorted(comparing((Fabricante f)->f.getProductos().size())
							//al reves
							.reversed())
					//formatear la salida
					.map(f -> String.format("%"+lf+"s",f.getNombre())+
							String.format("%"+lf+"s", f.getProductos().size()))
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes. 
	 * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 * Deben aparecer los fabricantes que no tienen productos.
	 */
	@Test
	void test39() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			listFab.stream()
					.map(f->{
						//el test37
						Double[] res=f.getProductos().stream()
							.map(p->new Double[]{p.getPrecio()})
								.reduce(new Double[]{Double.MIN_VALUE,Double.MAX_VALUE,0.0,0.0},(d1, d2)->
										new Double[]{
											Math.max(d1[0], d2[0]),
											Math.min(d1[1], d2[0]),
											(d1[2]*d1[3]+d2[0])/(d1[3]+1),
											d1[3]+1
								});
						//devolver resultado que hay que imprimir
						String resultado= "Fabricante: "+f.getNombre();
						//para que no salga MIN/MAX _value cuando no tienen producto
						resultado+="\n\tMax: "+(res[0]==Double.MIN_VALUE?0:res[0]);
						resultado+="\n\tMin: "+(res[1]==Double.MAX_VALUE?0:res[1]);
						resultado+="\n\tAvg: "+res[2];
						resultado+="\n\tCount: "+res[3];
						return resultado;
					}).forEach(System.out::println);

			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€. 
	 * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
	 */
	@Test
	void test40() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			listFab.stream()
					.map(f->{
						//igual que el test 37
						Double[] res=f.getProductos().stream()
							.map(p->new Double[]{p.getPrecio()})
								.reduce(new Double[]{Double.MIN_VALUE,Double.MAX_VALUE,0.0,0.0},(d1, d2)->new Double[]{
										Math.max(d1[0], d2[0]),
										Math.min(d1[1], d2[0]),
										(d1[2]*d1[3]+d2[0])/(d1[3]+1),
										d1[3]+1}
								);
						//preparar el resultado
						String resultado="";
						//comprobar la media mayor 200
						if (res[2]>200){
							//rellenar el resultado
							resultado+="Fabricante: "+f.getCodigo();
							//seguro van a tener algun producto
							//entonces no hace falta la ternaria
							resultado+="\n\tMax: "+res[0];
							resultado+="\n\tMin: "+res[1];
							resultado+="\n\tAvg: "+res[2];
							resultado+="\n\tCount: "+res[3]+"\n";

						}
						return resultado;
					}).forEach(System.out::print);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
	 */
	@Test
	void test41() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			listFab.stream()
					//filtrar cantidad producto mayor igual 2
					.filter(f->f.getProductos().size()>=2)
					//obtener nombre
					.map(Fabricante::getNombre)
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €. 
	 * Ordenado de mayor a menor número de productos.
	 */
	@Test
	void test42() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			listFab.stream()
					//ordenar por la cantidad de producto con precio mayor a 200
					.sorted(comparing((Fabricante f)->f.getProductos().stream()
								.filter(p->p.getPrecio()>=220)
								.count())
							//al reves
							.reversed())
					//nombre + cantidad de producto
					.map(f->f.getNombre()+" "+f.getProductos().stream()
							.filter(p->p.getPrecio()>=220)
							.count()
					)
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 43.oDevuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus prductos es superior a 1000 €
	 */
	@Test
	void test43() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			listFab.stream()
					//filtrar fabricante con suma de producto mayor a 1000
					.filter(f->f.getProductos().stream()
									.mapToDouble(Producto::getPrecio)
									.sum()>1000
							)
					//obtener nombre
					.map(Fabricante::getNombre)
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 * Ordenado de menor a mayor por cuantía de precio de los productos.
	 */
	@Test
	void test44() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			//se ha comprobado quitando el filter
			listFab.stream()
					//filtrar fabricante con suma de producto mayor a 1000
					.filter(f->f.getProductos().stream()
							.mapToDouble(Producto::getPrecio)
							.sum()>1000
					)
					//ordenar por suma de producto
					.sorted(comparing(f->f.getProductos().stream()
							.mapToDouble(Producto::getPrecio)
							.sum()))
					//obtener nombre
					.map(Fabricante::getNombre)
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante. 
	 * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante. 
	 * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
	 */
	@Test
	void test45() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			listFab.stream()
					//ordenar por nombre
					.sorted(comparing(Fabricante::getNombre))
					//obtener la frase que hay que imprimir
					.map((Fabricante f)->{
						//obtener el producto con el precio mas caro
						Producto p=f.getProductos().stream()
								.max(comparing(Producto::getPrecio))
								.orElse(null);
						//si no hay imprime el fabricante
						if (p==null)
						{
							return "Fabricante: "+f.getNombre();
						}
						//imprime el nombre,precio,fabricante
						return "Nombre:"+p.getNombre()+",Precio: "+p.getPrecio()+",Fabricante: "+f.getNombre();
					}).forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
			
	}
	
	/**
	 * 46. Devuelve un listado de todos los productos que tienen un precio mayor o igual a la media de todos los productos de su mismo fabricante.
	 * Se ordenará por fabricante en orden alfabético ascendente y los productos de cada fabricante tendrán que estar ordenados por precio descendente.
	 */
	@Test
	void test46() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
			//comprobado clonando el producto con el codigo 7 pero con diferente precio.
			//TODO STREAMS															
			listFab.stream()
					//ordenar por nombre
					.sorted(comparing(Fabricante::getNombre))
					//obtener la lista de producto
					.map(f->f.getProductos().stream()
											//filtrando por
											.filter(p->p.getPrecio()> f.getProductos().stream()
																						//la media del precio de los productos
																						.collect(averagingDouble(Producto::getPrecio)))
											//ordenar por precio al reves
											.sorted(comparing(Producto::getPrecio).reversed())
											.collect(toList())
					//obtener producto sin lista
					).flatMap(Collection::stream)
					.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
			
	}
	
}

