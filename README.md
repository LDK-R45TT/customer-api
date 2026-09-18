
# :trophy: CUSTOMER API: Validaciones y Excepciones en Spring Boot
___

En esta entrega se adelantó la parte de implementacion de validaciones, DTOs, manejo de excepciones y business logic en el service...

## 🧩 Estructura del Proyecto
___

```tree /F
src                                                            
└─ main                                                        
   ├─ java                                                     
   │  └─ com                                                   
   │     └─ bootcamp                                           
   │        └─ retocustomer                                    
   │           ├─ controller                                   
   │           │  └─ CustomerController.java                   
   │           ├─ dto                                          
   │           │  ├─ CustomerRequest.java                      
   │           │  └─ CustomerResponse.java                     
   │           ├─ entity                                       
   │           │  └─ Customer.java                             
   │           ├─ errors                                       
   │           │  └─ ErrorResponse.java                        
   │           ├─ exception                                    
   │           │  ├─ CustomerDeletionNotAllowedException.java  
   │           │  ├─ CustomerDuplicatedException.java          
   │           │  ├─ CustomerNotFoundException.java            
   │           │  └─ GlobalExceptionHandler.java               
   │           ├─ service                                      
   │           │  └─ CustomerService.java                      
   │           ├─ CustomerRepository.java                      
   │           └─ RetocustomerApplication.java                 
   └─ resources                                                
      ├─ static                                                
      ├─ templates                                             
      └─ application.properties                                

```

## 🧱 Implementaciones
___
### ✅Validaciones:
- **`dni`** (`String`)
    - **Restricciones:** No puede estar vacío, debe tener exactamente 8 caracteres y contener únicamente números.
    - **Mensajes de error:** _"El DNI no puede estar vacío"_, _"El DNI debe tener exactamente 8 dígitos"_, _"El DNI debe contener solo números"_.
- **`name`** (`String`)
    - **Restricciones:** No puede estar vacío y su longitud debe estar entre 3 y 50 caracteres.
    - **Mensajes de error:** _"el nombre no puede estar vacio"_, _"El nombre debe tener entre 3 y 50 caracteres"_.
- **`lastname`** (`String`)
    - **Restricciones:** Campo obligatorio. No se permiten textos vacíos o nulos.
    - **Mensaje de error:** _"el apellido no puede estar vacio"_.
- **`age`** (`Integer`)
    - **Restricciones:** Campo obligatorio. La edad permitida debe estar en el rango de 18 a 70 años inclusive.
    - **Mensajes de error:** _"El cliente debe ser mayor de edad (mínimo 18 años)"_, _"el cliente debe tener maximo 70 años"_.

### ✅Excepciones:
- __CustomerDeletionNotAllowedException:__  eliminar un usuario que su estado sea activo.
- __CustomerDuplicatedException: __ guardar un usuario con dni que ya existe en db.
- __CustomerNotFoundException:__ usuario no existe en db.
### ✅Controladores y Rutas:

1. __CUSTOMERS:__

| <center>Método HTTP</center> | <center>Ruta</center>       | <center>Descripción</center>                   | <center>Status</center> |
| ---------------------------- | --------------------------- | ---------------------------------------------- | ----------------------- |
| **`GET`**                    | `/customers/all`            | Obtiene lista completa de clientes             | `200 OK`                |
| **`GET`**                    | `/customers/{id}`           | Busca un cliente específico por su ID único    | `200 OK`                |
| **`GET`**                    | `/customers/por-nombre`     | Filtra y obtiene un cliente buscado por nombre | `302 FOUND`             |
| **`POST`**                   | `/customer/`                | Registra un nuevo cliente en el sistema        | `201 CREATED`           |
| **`POST`**                   | `/customer/all`             | Registra un grupo de clientes en el sistema    | `201 CREATED`           |
| **`PUT`**                    | `/customer/actualizar/{id}` | Actualiza un cliente por su ID                 | `200 OK`                |
| **`DELETE`**                 | `/customer/delete`          | Elimina un cliente del sistema                 | `200 OK`                |
| **`DELETE`**                 | `/customer/delete-all`      | Elimina todos los clientes                     | `200 OK`                |

## 📆 Branch utilizado
___

> Todos estos cambios se encuentran en la rama `reto-validation` del repositorio:  
>**[LDK-R45TT / customer-api (Branch: reto-validation)](https://github.com/LDK-R45TT/customer-api/commits/reto-validation)**