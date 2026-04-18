# 📋🛒 Sistema de Inventario y Ventas con Java y MySQL

<img width="1366" height="729" alt="image" src="https://github.com/user-attachments/assets/73a25fc1-f1c4-46f7-962a-35a4bd4a705e" />

Sistema de escritorio desarrollado en **Java Swing** con persistencia en **MySQL**, orientado a la gestión de inventario, clientes, usuarios y ventas en un pequeño negocio o minimarket. El sistema permite administrar productos por categorías, controlar stock, registrar ventas, generar facturas en PDF y emitir reportes.

# Módulos del sistema
## 1. Inicio de sesión

El sistema inicia con una pantalla de autenticación que permite ingresar mediante usuario o correo electrónico. Esta capa valida las credenciales registradas en la base de datos y permite restringir el acceso al sistema.

<img width="689" height="490" alt="image" src="https://github.com/user-attachments/assets/8e94fc89-aafb-47bb-a973-9111f4e53e23" />

## 2. Menú principal

El menú principal centraliza el acceso a todos los módulos del sistema. Desde aquí el usuario puede navegar entre registros, ventas, configuración y reportes.

<img width="1363" height="730" alt="image" src="https://github.com/user-attachments/assets/c1e68eb2-83b8-42ef-9c9a-90a09386b211" />

## 3. Gestión de usuarios

Este módulo permite registrar, modificar y eliminar usuarios del sistema. Cada usuario cuenta con información básica como nombre, username, correo, contraseña y rol.

<img width="556" height="507" alt="image" src="https://github.com/user-attachments/assets/ae9c8b69-fdff-4b39-8e56-76f6e2959fd8" />

### Funcionalidades
- registrar usuarios
- editar usuarios
- eliminar usuarios
- listar usuarios
- autenticación desde base de datos

## 4. Gestión de categorías

Permite registrar las categorías que servirán para clasificar los productos del inventario.

<img width="539" height="365" alt="image" src="https://github.com/user-attachments/assets/d310e479-f25d-45be-b1a6-b9d5bba15d16" />

### Funcionalidades
- registrar categorías
- modificar categorías
- eliminar categorías
- listar categorías

## 5. Gestión de productos

Este módulo administra los productos del inventario, almacenando nombre, precio, stock y categoría.

<img width="588" height="513" alt="image" src="https://github.com/user-attachments/assets/88791c08-5e32-4150-8db2-532b314adf91" />

### Funcionalidades
- registrar productos
- modificar productos
- eliminar productos
- asociar productos a categorías
- controlar stock disponible

## 6. Gestión de clientes

Permite registrar y administrar los clientes asociados a las ventas.

<img width="587" height="490" alt="image" src="https://github.com/user-attachments/assets/8a481f8d-4070-4c55-8980-74021a36601e" />

### Funcionalidades
- registrar clientes
- modificar clientes
- eliminar clientes
- listar clientes
- usar clientes dentro del proceso de venta

## 7. Configuración de empresa

Este módulo almacena los datos de la empresa, los cuales son utilizados posteriormente en la factura PDF.

<img width="578" height="370" alt="image" src="https://github.com/user-attachments/assets/5a9be3d8-241a-4b48-8fc5-687cbd115477" />

### Datos configurables
- RUC
- nombre de empresa
- dirección
- teléfono

## 8. Registro de ventas

El módulo de ventas permite construir un pedido seleccionando cliente, productos y cantidades. El sistema calcula subtotal, descuento, total, efectivo y cambio.

<img width="786" height="642" alt="image" src="https://github.com/user-attachments/assets/392955b4-2d12-495a-99f5-44e997ff3f09" />

### Funcionalidades
- selección de cliente
- búsqueda de productos
- control de stock disponible
- cálculo automático de importes
- registro de pedido y detalle
- descuento automático de stock

## 9. Factura PDF

Una vez registrada la venta, el sistema genera automáticamente una factura en PDF con los datos de la empresa, el cliente, los productos vendidos y los totales finales.

<img width="903" height="641" alt="image" src="https://github.com/user-attachments/assets/2a0e41cb-523a-4b25-b406-66ba24c36d19" />

### Contenido de la factura
- datos de empresa
- número de pedido
- fecha
- datos del cliente
- listado de productos
- subtotal
- descuento
- total a pagar
- efectivo
- cambio

## 10. Reportes del sistema

El proyecto incluye generación de reportes en PDF con diseño visual uniforme.

### Reporte de productos
<img width="903" height="571" alt="image" src="https://github.com/user-attachments/assets/e906a2c0-b09b-4a4b-ac0e-3af580803861" />
  
### Reporte de categorías
<img width="899" height="574" alt="image" src="https://github.com/user-attachments/assets/2b563afa-07a4-4dcf-8746-5dc3a5f8f1e9" />

### Reporte de clientes
<img width="908" height="542" alt="image" src="https://github.com/user-attachments/assets/2aadf085-11ea-40fb-9358-336a2b4bbb07" />

### Reporte de ventas
<img width="903" height="640" alt="image" src="https://github.com/user-attachments/assets/31c69ab3-f97c-40d0-a888-38b9dcb57f08" />

# Base de datos

La persistencia del sistema se encuentra implementada en MySQL.
## Tablas principales

- `usuarios`
- `categorias`
- `productos`
- `clientes`
- `empresas`
- `pedidos`
- `detalle_pedido`

## Relaciones importantes

- un producto pertenece a una categoría
- un pedido pertenece a un cliente
- un pedido tiene múltiples ítems en `detalle_pedido`
- cada ítem está asociado a un producto

## Script SQL

La carpeta `database` contiene el script principal de instalación de la base de datos.

```text
database/
└── setup.sql


