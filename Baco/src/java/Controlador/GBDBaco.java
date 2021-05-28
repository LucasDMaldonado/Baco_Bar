package Controlador;
import Modelo.Categoria;
import Modelo.DTOMesaXPedido;
import Modelo.DTOPedido;
import Modelo.DTOTragoxReceta;
import Modelo.DTOVenta;
import Modelo.Mesa;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.Trago;
import Modelo.Usuario;
import Modelo.Stock;
import Modelo.TragoxCant;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

public class GBDBaco {
    
    private Connection con;
    @SuppressWarnings("FieldMayBeFinal")
    private String URL = "jdbc:sqlserver://DESKTOP-0CJ18MR;databaseName=Baco_Bar";
    private String user = "sa";
    private String Pass = "1234";
    
    private void abrirConexion()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(URL, user, Pass);
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }    
    private void cerrarConexion()
    {
        try
        {
            if(con != null && !con.isClosed())
                con.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }    
    public void agregarProducto(Producto p)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Productos(Nombre_Producto,Ingrediente) VALUES(?,?)");
            ps.setString(1,p.getNombre());
            ps.setBoolean(2,p.isIngrediente());
            ps.executeUpdate();
            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
    }
    public void agregarTrago(Trago T)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Tragos(Nombre_Trago,Disponible,Precio,Id_Categoria) VALUES(?,?,?,?)");
            ps.setString(1,T.getNombre());
            ps.setBoolean(2,T.isDisponible());
            ps.setDouble(3,T.getPrecio());
            ps.setInt(4,T.getCategoria());
            ps.executeUpdate();
            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
    }
    public void agregarUsuario(Usuario usr)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Usuarios(Id_Usuario,Password) VALUES(?,?)");
            ps.setString(1,usr.getUser());
            ps.setString(2,usr.getPass());           
            ps.executeUpdate();
            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
    }
    public void agregarVenta(DTOVenta V)
    {
        
        ArrayList<Trago> trago = V.getTrago();
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Ventas(Id_mesa,Total,Fecha,id_pago,Pagado) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,V.getVenta().getId_Mesa());
            ps.setDouble(2,V.getVenta().getTotal());
            ps.setDate(3,date);
            ps.setInt(4,V.getVenta().getId_pago());
            ps.setBoolean(5,V.getVenta().isPagado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) throw new RuntimeException("no devolvió el ID");

                    int idVenta = rs.getInt(1);
                    for (Trago trag : trago) 
                    {
                        ps = con.prepareStatement("INSERT INTO Detalles_venta (id_Venta,Id_Pedido,) VALUES (?,?)");
                        ps.setInt(1,idVenta);
                        ps.setInt(2,trag.getId());
                        ps.setInt(3,trag.getCantidad());
                        ps.setDouble(4,trag.getPrecio());
                        ps.addBatch();
                    }
                ps.executeUpdate();
                rs.close();
                }
            catch(Exception exc)
                {
                    exc.printStackTrace();
                }
            }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
    } 
    public void agregarPedido (Pedido p)
    {
        LocalDate Fecha = LocalDate.now();
        Date date = Date.valueOf(Fecha);
        ArrayList<Trago> trago = V.getTrago();
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Ventas(Id_mesa,Total,Fecha,id_pago,Pagado) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,V.getVenta().getId_Mesa());
            ps.setDouble(2,V.getVenta().getTotal());
            ps.setDate(3,date);
            ps.setInt(4,V.getVenta().getId_pago());
            ps.setBoolean(5,V.getVenta().isPagado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) throw new RuntimeException("no devolvió el ID");

                    int idVenta = rs.getInt(1);
                    for (Trago trag : trago) 
                    {
                        ps = con.prepareStatement("INSERT INTO Detalles_venta (id_Venta,Id_Pedido,) VALUES (?,?)");
                        ps.setInt(1,idVenta);
                        ps.setInt(2,trag.getId());
                        ps.setInt(3,trag.getCantidad());
                        ps.setDouble(4,trag.getPrecio());
                        ps.addBatch();
                    }
                ps.executeUpdate();
                rs.close();
                }
            catch(Exception exc)
                {
                    exc.printStackTrace();
                }
            }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
    }    
    public ArrayList<DTOTragoxReceta> obtenerTragosxReceta()
        {
        ArrayList<Trago> trago= new ArrayList<>();
        ArrayList<DTOTragoxReceta> DTOTxI = new ArrayList<>();
        ArrayList<Producto> ingredientes = new ArrayList<>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT T.*,C.Categoria FROM Tragos T JOIN Categorias C ON T.Id_Categoria = C.Id_Categoria");
            while(rs.next())
            {
                int id = rs.getInt("Id_Trago");
                String nombre = rs.getString("Nombre_Trago");
                boolean disp = rs.getBoolean("Disponible");
                double precio = rs.getDouble("Precio");
                int idcat = rs.getInt("Id_Categoria");
                String cat= rs.getString("Categoria");
                Trago trag = new Trago(id, nombre, disp, precio, idcat);
                trago.add(trag);
                try
                {
                    PreparedStatement ps = con.prepareStatement("SELECT P.* FROM Recetas R JOIN Tragos T ON R.Id_trago = T.Id_Trago LEFT JOIN Productos P ON R.Id_Producto= P.Id_Producto WHERE T.Id_Trago =? ");
                    ps.setInt(1, id);
                    ResultSet rs2 = ps.executeQuery();
                    while(rs.next())
                    {
                        int idprod = rs2.getInt("Id_Producto");
                        String nomprod = rs2.getString("Nombre_Producto");
                        boolean ingrediente = rs2.getBoolean("Ingrediente");


                       Producto p = new Producto(id, nombre, ingrediente);
                        ingredientes.add(p);
                    }
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
                
               

               
                DTOTragoxReceta dto = new DTOTragoxReceta(trag, ingredientes,cat);
                DTOTxI.add(dto);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return DTOTxI;
    }
    public DTOTragoxReceta obtenerTragosxRecetaxId(int idt)
    {   
        DTOTragoxReceta dto = null;
        ArrayList<Producto> ingredientes = new ArrayList<>();
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("SELECT T.*,C.Categoria FROM Tragos T JOIN Categorias C ON T.Id_Categoria = C.Id_Categoria  WHERE Id_Trago ?");
            ps.setInt(1, idt);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("Id_Trago");
                String nombre = rs.getString("Nombre_Trago");
                boolean disp = rs.getBoolean("Disponible");
                double precio = rs.getDouble("Precio");
                int idcat =rs.getInt("Id_Categoria");
                String cat= rs.getString("Categoria");
                Trago trag = new Trago(id, nombre, disp, precio, idcat);
                
                try
                {
                    PreparedStatement ps2 = con.prepareStatement("SELECT P.* FROM Recetas R JOIN Tragos T ON R.Id_trago = T.Id_Trago LEFT JOIN Productos P ON R.Id_Producto= P.Id_Producto WHERE T.Id_Trago =? ");
                    ps2.setInt(1, id);
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs.next())
                    {
                        int idprod = rs2.getInt("Id_Producto");
                        String nomprod = rs2.getString("Nombre_Producto");
                        boolean ingrediente = rs2.getBoolean("Ingrediente");


                       Producto p = new Producto(id, nombre, ingrediente);
                        ingredientes.add(p);
                    }
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
                DTOTragoxReceta DTO= new DTOTragoxReceta(trag, ingredientes,cat);
                dto = DTO;
               }
            rs.close();
            
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return dto;
    }
    public ArrayList<DTOMesaXPedido> obtenerMesasxPedido()
    {
        ArrayList<TragoxCant> listaT = new ArrayList<>();
        ArrayList<DTOMesaXPedido> lista = new ArrayList<>();
        try
        {
            abrirConexion();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT M.*,P.Id_pedido FROM Mesas M JOIN Pedidos P ON M.Id_Mesas = P.Id_Mesa WHERE P.Estado = 1");
            while(rs.next())
            {
                int idMesa = rs.getInt("Id_Mesas");
                String Nombre = rs.getString("Nombre");
                String Ubicacion = rs.getString("Ubicacion");
                int Pedido = rs.getInt("Id_pedido");                
                Mesa M = new Mesa(Pedido, Nombre, Ubicacion, Pedido);
                
                try
                {
                    PreparedStatement ps2 = con.prepareStatement("SELECT P.* FROM Pedidos P JOIN Mesas M ON P.Id_Mesa = M.Id_Mesas WHERE P.Id_pedido = ? ");
                    ps2.setInt(1, Pedido);
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next())
                    {
                        int idPedido = rs2.getInt("Id_pedido");
                        int idMes = rs2.getInt("Id_Mesa");
                        Date fecha = rs2.getDate("Tiempo");
                        boolean Estado = rs2.getBoolean("Estado");
                        Pedido p = new Pedido(idPedido, idMesa, Estado, fecha);
                        try
                        {
                            PreparedStatement ps3 = con.prepareStatement("SELECT D.Id_Trago,T.Nombre_Trago,D.cantidad,T.Precio FROM Detalle_Pedidos D JOIN Pedidos P ON D.Id_Pedido = P.Id_pedido JOIN Tragos T ON D.Id_Trago = T.Id_Trago WHERE D.Id_Pedido =? ");
                            ps3.setInt(1, idPedido);
                            ResultSet rs3 = ps3.executeQuery();
                            while(rs3.next())
                            {
                                int idTrago = rs3.getInt("Id_Trago");
                                String nomTrago = rs3.getString("Nombre_Trago");
                                int cant = rs3.getInt("cantidad");
                                double precio = rs3.getDouble("Precio");

                                TragoxCant t =  new TragoxCant(idTrago, nomTrago, cant, precio);
                                listaT.add(t);
                            }
                            
                        }
                        catch(Exception exc)
                        {
                            exc.printStackTrace();
                        }
                        DTOPedido P = new DTOPedido(p, listaT);
                       DTOMesaXPedido MxP = new DTOMesaXPedido(M, P);
                       lista.add(MxP);
                    }
                    
                }                             
                
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
    public ArrayList<Stock> obtenerStock(int caso)
    {
        ArrayList<Stock> lista = new ArrayList<>();
        try
        {
            if (caso==1) 
            {
                abrirConexion();
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT S.*,P.Nombre_Producto, P.Ingrediente FROM Stocks S  JOIN Productos P ON S.Id_producto = P.Id_Producto");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Stock");
                    int cant = rs.getInt("Cantidad");
                    int usado = rs.getInt("Usado");
                    int idProd = rs.getInt("Id_producto");
                    String producto = rs.getString("Nombre_Producto");
                    String proveedor = rs.getString("Proveedor");
                    boolean ingrediente = rs.getBoolean("Ingrediente");
                    Date fecha = rs.getDate("Fecha_Actualizacion");

                    Stock S = new Stock(id, cant, usado, proveedor, producto, idProd, ingrediente, fecha);
                    lista.add(S);
                }
                 rs.close();
            }
            else if (caso == 2) {
                abrirConexion();
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT S.*,P.Nombre_Producto, P.Ingrediente FROM Stocks S  JOIN Productos P ON S.Id_producto = P.Id_Producto WHERE P.Ingrediente = 1");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Stock");
                    int cant = rs.getInt("Cantidad");
                    int usado = rs.getInt("Usado");
                    int idProd = rs.getInt("Id_producto");
                    String producto = rs.getString("Nombre_Producto");
                    String proveedor = rs.getString("Proveedor");
                    boolean ingrediente = rs.getBoolean("Ingrediente");
                    Date fecha = rs.getDate("Fecha_Actualizacion");

                    Stock S = new Stock(id, cant, usado, proveedor, producto, idProd, ingrediente, fecha);
                    lista.add(S);
                }
                 rs.close();
            }
            else if (caso == 3) {
                abrirConexion();
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT S.*,P.Nombre_Producto, P.Ingrediente FROM Stocks S  JOIN Productos P ON S.Id_producto = P.Id_Producto WHERE P.Ingrediente = 0");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Stock");
                    int cant = rs.getInt("Cantidad");
                    int usado = rs.getInt("Usado");
                    int idProd = rs.getInt("Id_producto");
                    String producto = rs.getString("Nombre_Producto");
                    String proveedor = rs.getString("Proveedor");
                    boolean ingrediente = rs.getBoolean("Ingrediente");
                    Date fecha = rs.getDate("Fecha_Actualizacion");

                    Stock S = new Stock(id, cant, usado, proveedor, producto, idProd, ingrediente, fecha);
                    lista.add(S);
            }
             rs.close();
            }
            
           
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
    public ArrayList<Categoria> obtenerCategorias()
    {
        ArrayList<Categoria> lista = new ArrayList<>();
        try
        {
           abrirConexion();
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT * FROM Categorias");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Categoria");               
                    String cat = rs.getString("Categoria");
                    Categoria c = new Categoria(id, cat);
                    lista.add(c);
                }
                 rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
        public ArrayList<Producto> obtenerIngredientes()
    {
        ArrayList<Producto> lista = new ArrayList<>();
        try
        {
           abrirConexion();
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT * FROM Productos WHERE Ingrediente = 1");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Producto");               
                    String nombre = rs.getString("Nombre_Producto");
                    boolean ing = rs.getBoolean("ingrediente");
                    Producto p = new Producto(id, nombre, ing);
                    lista.add(c);
                }
                 rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
     public ArrayList<Oferta> obtenerVentas(int idComercio)
    {
        ArrayList<Oferta> lista = new ArrayList<Oferta>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT O.idOferta,O.fechaInicio,O.DiasVigencia,O.idComercio,O.NombreOferta,O.Precio,O.PrecioOferta,O.idImagen,rtrim(ltrim(I.NombreImagen))'NombreImagen'\n" +
                                                        "FROM Ofertas O\n" +
                                                        "JOIN Imagenes I ON O.idImagen=I.idImagen\n" +
                                                        "WHERE O.idComercio=?");
            st.setInt(1, idComercio);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idOf = rs.getInt("idOferta");
                String fechaini = rs.getString("fechaInicio");
                String nomOf = rs.getString("NombreOferta");
                int idcome = rs.getInt("idComercio");
                int dias = rs.getInt("DiasVigencia");
                double precio = rs.getDouble("Precio");
                double precioOf= rs.getDouble("PrecioOferta");
                Imagen img = new Imagen(rs.getInt("idImagen"),rs.getString("NombreImagen"));
                             
                Oferta R = new Oferta(idOf, nomOf, idcome, fechaini, dias, precio, precioOf, img);
                lista.add(R);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
     public Stock obtenerStockxId(int idStock)
    {
        Stock stock = null;
        try
        {
            abrirConexion();
           PreparedStatement ps = con.prepareStatement("SELECT S.*,P.Nombre_Producto, P.Ingrediente FROM Stocks S  JOIN Productos P ON S.Id_producto = P.Id_Producto WHERE S.Id_Stock = ?");
           ps.setInt(1, idStock);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int id = rs.getInt("Id_Stock");
                    int cant = rs.getInt("Cantidad");
                    int usado = rs.getInt("Usado");
                    int idProd = rs.getInt("Id_producto");
                    String producto = rs.getString("Nombre_Producto");
                    String proveedor = rs.getString("Proveedor");
                    boolean ingrediente = rs.getBoolean("Ingrediente");
                    Date fecha = rs.getDate("Fecha_Actualizacion");

                    stock = new Stock(id, cant, usado, proveedor, producto, idProd, ingrediente, fecha);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return stock;
    } 
     public Usuario ValidarUser(Usuario User1)
    {
       Usuario user = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT * FROM Usuarios WHERE Id_Usuario = ? AND Password = ?");
            st.setString(1, User1.getUser());
            st.setString(2, User1.getPass());
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
              
                String Usuario = rs.getString("Id_Usuario");
                String Password = rs.getString("Password");
               
                
                user = new Usuario(Usuario, Password);
                
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return user;
    } 
     public ArrayList<DTOComercio> obtenerProductoMasUsado()
    {
        ArrayList<DTOComercio> lista = new ArrayList<DTOComercio>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT C.*,RTRIM(LTRIM(I.NombreImagen))'NOMBRE IMAGEN',RTRIM(LTRIM(R.Descripcion))'NOMBRE RUBRO' \n" +
                                            "FROM Comercios C \n" +
                                            "JOIN Imagenes I ON I.idImagen =C.idImagen\n" +
                                            "JOIN Rubros R ON R.idRubro =C.idRubro ");
            while(rs.next())
            {
                int idcome = rs.getInt("idComercio");
                int idrubro = rs.getInt("idRubro");
                String nombre = rs.getString("Nombre");
                int iduser= rs.getInt("idUsuario");
                Imagen img = new Imagen(rs.getInt("idImagen"), rs.getString("NOMBRE IMAGEN"));
                String nomRubro = rs.getString("NOMBRE RUBRO");
                DTOComercio R = new DTOComercio(idcome, nombre, iduser, idrubro, img, nomRubro);
                lista.add(R);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
    public ArrayList<Oferta> obtenerVentaMensual()
    {
        ArrayList<Oferta> lista = new ArrayList<Oferta>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT O.idOferta,O.fechaInicio,O.DiasVigencia,O.idComercio,O.NombreOferta,O.Precio,O.PrecioOferta,O.idImagen,rtrim(ltrim(I.NombreImagen))'NombreImagen'\n" +
                                           "FROM Ofertas O\n" +
                                           "JOIN Imagenes I ON O.idImagen=I.idImagen");
            while(rs.next())
            {
                int idOf = rs.getInt("idOferta");
                String fechaini = rs.getString("fechaInicio");
                String nomOf = rs.getString("NombreOferta");
                int idcome = rs.getInt("idComercio");
                int dias = rs.getInt("DiasVigencia");
                double precio = rs.getDouble("Precio");
                double precioOf= rs.getDouble("PrecioOferta");
                Imagen img = new Imagen(rs.getInt("idImagen"),rs.getString("NombreImagen"));
                             
                Oferta R = new Oferta(idOf, nomOf, idcome, fechaini, dias, precio, precioOf, img);
                lista.add(R);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
    public ArrayList<DTOPromValxcomercio> obtenerpromValxCom()
    {
       ArrayList<DTOPromValxcomercio> prom = new ArrayList<DTOPromValxcomercio>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT ISNULL(AVG(C.idValoracion),0) 'PROMEDIO', ISNULL(COUNT(C.idValoracion),0)'CANTIDAD',RTRIM(LTRIM(CO.Nombre))'NOMBRE',CO.idComercio\n" +
                                            "FROM Comercios CO \n" +
                                            "LEFT JOIN Comentarios C  ON C.idComercio =CO.idComercio\n" +
                                            "GROUP BY CO.idComercio,CO.Nombre\n" +
                                            "ORDER BY  NOMBRE ASC");
            while(rs.next())
            {
                int cant = rs.getInt("CANTIDAD");
                double prom1 = rs.getDouble("PROMEDIO");  
                int idcome = rs.getInt("idComercio");
                String nomcome1 = rs.getString("NOMBRE");
                DTOPromValxcomercio pvc =new DTOPromValxcomercio(cant, prom1, idcome, nomcome1);
                prom.add(pvc);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return prom;
    }
    public ArrayList<Comentario> obtenerListaComentario()
    {
        ArrayList<Comentario> lista = new ArrayList<Comentario>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Comentarios");
            while(rs.next())
            {
                int idcoment = rs.getInt("idComentario");
                String nombre = rs.getString("Nombre");
                String Comentario = rs.getString("Comentario");
                int idComercio = rs.getInt("idComercio");
                int idValoracion = rs.getInt("idValoracion");
                boolean Resp = rs.getBoolean("Respondido");
                int iduser = rs.getInt("idUsuario");
                String nombreRta = rs.getString("NombreRta");
                String rta = rs.getString("Respuesta");
                
                Comentario comentario = new Comentario(idcoment, nombre, idComercio, idValoracion, Comentario, Resp, nombreRta, iduser, rta);
                lista.add(comentario);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
     public ArrayList<DTOCantCmtXCome> obtenerCantComentarioXComercio()
    {
        ArrayList<DTOCantCmtXCome> lista = new ArrayList<DTOCantCmtXCome>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(C.idComentario)'CANTIDAD',RTRIM(LTRIM( CO.Nombre))'NOMBRE'\n" +
                                            "FROM Comentarios C\n" +
                                            "JOIN Comercios CO ON C.idComercio = CO.idComercio\n" +
                                            "GROUP BY CO.Nombre\n" +
                                            "ORDER BY CANTIDAD DESC");
            while(rs.next())
            {

                String nombre = rs.getString("NOMBRE");
                int cant= rs.getInt("CANTIDAD");
                             
                DTOCantCmtXCome R = new DTOCantCmtXCome(nombre, cant);
                lista.add(R);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
     public ArrayList<DTOCantXEstrella> obtenerCantEstrellas()
    {
        ArrayList<DTOCantXEstrella> lista = new ArrayList<DTOCantXEstrella>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT RTRIM(LTRIM( V.Descripcion))'NOMBRE', ISNULL(COUNT(C.idValoracion),0)'CANTIDAD'\n" +
                                            "FROM Valoraciones V\n" +
                                            "LEFT JOIN Comentarios C  ON C.idValoracion = v.idValoracion\n" +
                                            "GROUP BY V.Descripcion\n" +
                                            "ORDER BY V.Descripcion DESC");
            while(rs.next())
            {

                String nombre = rs.getString("NOMBRE");
                int cant= rs.getInt("CANTIDAD");
                             
                DTOCantXEstrella R = new DTOCantXEstrella(nombre, cant);
                lista.add(R);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
     public void eliminarComercio(int idcome)
    {
     
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("DELETE Comercios WHERE idComercio = ?");
            st.setInt(1, idcome);
            st.executeUpdate();            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
    }
     public void actualizarTrago(Comercio cmr)
    {
        if (cmr.getImg().getIdImagen()==0) {
            try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DECLARE @IDIMG INT\n" +
                                                        "BEGIN TRANSACTION\n" +
                                                        "INSERT INTO Imagenes(NombreImagen) VALUES (?)\n" +
                                                        "SET @IDIMG = @@IDENTITY\n" +
                                                        "UPDATE Comercios\n" +
                                                        "SET Nombre = ?, idRubro= ?,idImagen = @IDIMG, idUsuario = ? \n" +
                                                        "WHERE idComercio = ?\n" +
                                                        "COMMIT TRANSACTION");
            ps.setString(1, cmr.getImg().getnombreImagen());
            ps.setString(2, cmr.getNombre());
            ps.setInt(3, cmr.getRubro());
            ps.setInt(4, cmr.getUser());
            ps.setInt(5, cmr.getIdComercio());
            ps.executeUpdate();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
            
        }
        else{
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Comercios \n" +
                                                        "SET Nombre = ?, idRubro= ?,idImagen = ?, idUsuario = ? \n" +
                                                        "WHERE idComercio = ?");
            ps.setString(1, cmr.getNombre());
            ps.setInt(2, cmr.getRubro());
            ps.setInt(3, cmr.getImg().getIdImagen());
            ps.setInt(4, cmr.getUser());
            ps.setInt(5, cmr.getIdComercio());
            ps.executeUpdate();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        }
           
    }
     public ArrayList<DTORubro> obtenerRubrox()
    {
        ArrayList<DTORubro> lista = new ArrayList<DTORubro>();
        try
        {
            abrirConexion();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT R.idRubro,rtrim(ltrim(R.Descripcion))'Descripcion',R.idImagen,rtrim(ltrim(I.NombreImagen))'NombreImagen' FROM Rubros R JOIN Imagenes I ON R.idImagen=I.idImagen");
            while(rs.next())
            {
                int idRubro = rs.getInt("idRubro");
                String Descripcion = rs.getString("Descripcion");
                int idimg = rs.getInt("idRubro");
                String img = rs.getString("NombreImagen");                             
                DTORubro R = new DTORubro(idRubro, idimg, img, Descripcion);
                lista.add(R);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
     public void agregarRubro(DTORubro R)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DECLARE @IDIMG INT\n" +
                                                        " BEGIN TRANSACTION\n" +
                                                        "INSERT INTO Imagenes(NombreImagen) VALUES (?)\n" +
                                                        "SET @IDIMG = @@IDENTITY\n" +
                                                        "INSERT INTO Rubros(Descripcion,idImagen) VALUES (?,@IDIMG)\n" +
                                                        " COMMIT TRANSACTION");
            ps.setString(1,R.getImg());
            ps.setString(2,R.getDescripcion());
            ps.executeUpdate();
            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
    } 
    public void actualizarStock(Stock S)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("BEGIN TRANSACTION\n" +
                                                        "UPDATE Stocks SET Cantidad =?,Usado =?, Proveedor=?,Id_producto=?,Fecha_Actualizacion=GETDATE()  WHERE Id_Stock = ?\n" +
                                                        "UPDATE Productos SET  Nombre_Producto = ?, Ingrediente = ? WHERE Id_Producto = ?\n" +
                                                        "COMMIT TRANSACTION");
            ps.setInt(1,S.getCantidad() );
            ps.setInt(2, S.getUsado());
            ps.setString(3, S.getProveedor());
            ps.setInt(4, S.getId_producto());
            ps.setInt(5, S.getId());
            ps.setString(6, S.getNomProd());
            ps.setBoolean(7, S.isIngrediente());
            ps.setInt(8, S.getId_producto());
            ps.executeUpdate();
            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
           
    }
     public void eliminarTrago(int id)
    {
     
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("DELETE Rubros WHERE idRubro = ?");
            st.setInt(1, id);
            st.executeUpdate();            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
    }
      public void agregarOferta(Oferta of)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DECLARE @IDIMG INT\n" +
                                                        "BEGIN TRANSACTION\n" +
                                                        "INSERT INTO Imagenes(NombreImagen) VALUES (?)\n" +
                                                        "SET @IDIMG = @@IDENTITY\n" +
                                                        "INSERT INTO Ofertas (NombreOferta,fechaInicio,DiasVigencia,idComercio,Precio,PrecioOferta,idImagen) VALUES (?,?,?,?,?,?,@IDIMG)\n" +
                                                        "COMMIT TRANSACTION");
            ps.setString(1, of.getImg().getnombreImagen());
            ps.setString(2, of.getNombreOferta());
            ps.setString(3, of.getFechaInicio());
            ps.setInt(4, of.getDiasVigencia());
            ps.setInt(5, of.getIdcome());
            ps.setDouble(6, of.getPrecio());
            ps.setDouble(7, of.getPrecioOferta());
            ps.executeUpdate();
            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
    } 
    public void actualizarOferta (Oferta of)
    {
        if (of.getImg().getIdImagen()==0) {
            try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DECLARE @IDIMG INT\n" +
                                                        " BEGIN TRANSACTION\n" +
                                                        "INSERT INTO Imagenes(NombreImagen) VALUES (?)\n" +
                                                        "SET @IDIMG = @@IDENTITY\n" +
                                                        " UPDATE Ofertas SET NombreOferta =?,fechaInicio =?, DiasVigencia=?,idComercio=?,Precio=?,PrecioOferta=?, idImagen= @IDIMG\n" +
                                                        " WHERE idOferta = ?\n" +
                                                        " COMMIT TRANSACTION");
            ps.setString(1, of.getImg().getnombreImagen());
            ps.setString(2, of.getNombreOferta());
            ps.setString(3, of.getFechaInicio());
            ps.setInt(4, of.getDiasVigencia());
            ps.setInt(5, of.getIdcome());
            ps.setDouble(6, of.getPrecio());
            ps.setDouble(7, of.getPrecioOferta());
            ps.setInt(8, of.getIdoferta());
            ps.executeUpdate();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
            
        }
        else{
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement(" UPDATE Ofertas SET NombreOferta =?,fechaInicio =?, DiasVigencia=?,idComercio=?,Precio=?,PrecioOferta=?, idImagen=?\n" +
                                                        " WHERE idOferta = ?");
            ps.setString(1,of.getNombreOferta() );
            ps.setString(2, of.getFechaInicio());
            ps.setInt(3, of.getDiasVigencia());
            ps.setInt(4, of.getIdcome());
            ps.setDouble(5, of.getPrecio());
            ps.setDouble(6, of.getPrecioOferta());
            ps.setInt(7,of.getImg().getIdImagen() );
            ps.setInt(8, of.getIdoferta());
            ps.executeUpdate();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        }
           
    }
     public void eliminarPorudcto(int id)
    {
     
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("DELETE Ofertas WHERE idOferta = ?");
            st.setInt(1, id);
            st.executeUpdate();            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
    }
     public void eliminarComentario(int idcome)
    {
     
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("DELETE Comentarios WHERE idComentario = ?");
            st.setInt(1, idcome);
            st.executeUpdate();            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
    }
     
}

