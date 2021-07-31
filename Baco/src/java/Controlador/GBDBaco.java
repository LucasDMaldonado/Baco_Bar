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
    public void agregarStock(Stock S)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DECLARE @IDPROD INT\n" +
                                                        "BEGIN TRANSACTION\n" +
                                                        "INSERT INTO Productos(Nombre_Producto,Ingrediente) VALUES(?,?)\n" +
                                                        "SET @IDPROD = @@IDENTITY\n" +
                                                        "INSERT INTO Stocks (Cantidad,Usado,Proveedor,Id_producto,Fecha_Actualizacion)VALUES(?,?,?,@IDPROD,GETDATE())\n" +
                                                        "COMMIT TRANSACTION");
            ps.setString(1,S.getNomProd());
            ps.setBoolean(2,S.isIngrediente());
            ps.setInt(3,S.getCantidad());
            ps.setInt(4,S.getUsado());
            ps.setString(5,S.getProveedor());
            
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
    public void agregarMesa(Mesa M)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Mesas (Nombre, Ubicacion) VALUES (?,?)");
            ps.setString(1,M.getNombre());
            ps.setString(2,M.getUbicacion());           
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
//    public void agregarVenta(DTOVenta V)
//    {
//        
//        ArrayList<Trago> trago = V.getTrago();
//        try
//        {
//            abrirConexion();
//            PreparedStatement ps = con.prepareStatement("INSERT INTO Ventas(Id_mesa,Total,Fecha,id_pago,Pagado) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1,V.getVenta().getId_Mesa());
//            ps.setDouble(2,V.getVenta().getTotal());
//            ps.setDate(3,date);
//            ps.setInt(4,V.getVenta().getId_pago());
//            ps.setBoolean(5,V.getVenta().isPagado());
//            ps.executeUpdate();
//            try (ResultSet rs = ps.getGeneratedKeys()) {
//                    if (!rs.next()) throw new RuntimeException("no devolvió el ID");
//
//                    int idVenta = rs.getInt(1);
//                    for (Trago trag : trago) 
//                    {
//                        ps = con.prepareStatement("INSERT INTO Detalles_venta (id_Venta,Id_Pedido,) VALUES (?,?)");
//                        ps.setInt(1,idVenta);
//                        ps.setInt(2,trag.getId());
//                        ps.setInt(3,trag.getCantidad());
//                        ps.setDouble(4,trag.getPrecio());
//                        ps.addBatch();
//                    }
//                ps.executeUpdate();
//                rs.close();
//                }
//            catch(Exception exc)
//                {
//                    exc.printStackTrace();
//                }
//            }
//        
//        catch(Exception exc)
//        {
//            exc.printStackTrace();
//        }
//        finally
//        {
//            cerrarConexion();
//        }
//    } 
//    public void agregarPedido (Pedido p)
//    {
//        ArrayList<Trago> trago = V.getTrago();
//        try
//        {
//            abrirConexion();
//            PreparedStatement ps = con.prepareStatement("INSERT INTO Ventas(Id_mesa,Total,Fecha,id_pago,Pagado) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1,V.getVenta().getId_Mesa());
//            ps.setDouble(2,V.getVenta().getTotal());
//            ps.setDate(3,date);
//            ps.setInt(4,V.getVenta().getId_pago());
//            ps.setBoolean(5,V.getVenta().isPagado());
//            ps.executeUpdate();
//            try (ResultSet rs = ps.getGeneratedKeys()) {
//                    if (!rs.next()) throw new RuntimeException("no devolvió el ID");
//
//                    int idVenta = rs.getInt(1);
//                    for (Trago trag : trago) 
//                    {
//                        ps = con.prepareStatement("INSERT INTO Detalles_venta (id_Venta,Id_Pedido,) VALUES (?,?)");
//                        ps.setInt(1,idVenta);
//                        ps.setInt(2,trag.getId());
//                        ps.setInt(3,trag.getCantidad());
//                        ps.setDouble(4,trag.getPrecio());
//                        ps.addBatch();
//                    }
//                ps.executeUpdate();
//                rs.close();
//                }
//            catch(Exception exc)
//                {
//                    exc.printStackTrace();
//                }
//            }
//        
//        catch(Exception exc)
//        {
//            exc.printStackTrace();
//        }
//        finally
//        {
//            cerrarConexion();
//        }
//    }    
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
                    if (rs!=null) {
                    while(rs2.next())
                    {
                        int idprod = rs2.getInt("Id_Producto");
                        String nomprod = rs2.getString("Nombre_Producto");
                        boolean ingrediente = rs2.getBoolean("Ingrediente");


                       Producto p = new Producto(id, nomprod, ingrediente);
                        ingredientes.add(p);
                    }
                    }

                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
                
               

               
                DTOTragoxReceta dto = new DTOTragoxReceta(trag, ingredientes,idcat);
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


                       Producto p = new Producto(id, nomprod, ingrediente);
                        ingredientes.add(p);
                    }
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
                DTOTragoxReceta DTO= new DTOTragoxReceta(trag, ingredientes,idcat);
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
                        Time fecha = rs2.getObject("Tiempo", Time.class);
                        boolean Estado = rs2.getBoolean("Estado");
                        Pedido p = new Pedido(idPedido, idMes, Estado, fecha);
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
            catch(Exception exc)
                {
                    exc.printStackTrace();
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
                ResultSet rs = ps.executeQuery("SELECT S.Id_Stock,(S.Cantidad-S.Usado) AS Stock,S.Proveedor,S.Id_producto,S.Fecha_Actualizacion,P.Nombre_Producto, P.Ingrediente FROM Stocks S  JOIN Productos P ON S.Id_producto = P.Id_Producto");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Stock");
                    int cant = rs.getInt("Stock");
                    int idProd = rs.getInt("Id_producto");
                    String producto = rs.getString("Nombre_Producto");
                    String proveedor = rs.getString("Proveedor");
                    boolean ingrediente = rs.getBoolean("Ingrediente");
                    Date fecha = rs.getDate("Fecha_Actualizacion");

                    Stock S = new Stock(id, cant, 0, proveedor, producto, idProd, ingrediente, fecha);
                    lista.add(S);
                }
                 rs.close();
            }
            else if (caso == 2) {
                abrirConexion();
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT S.Id_Stock,(S.Cantidad-S.Usado) AS Stock,S.Proveedor,S.Id_producto,S.Fecha_Actualizacion,P.Nombre_Producto, P.Ingrediente FROM Stocks S  JOIN Productos P ON S.Id_producto = P.Id_Producto WHERE P.Ingrediente = 1");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Stock");
                    int cant = rs.getInt("Stock");
                    int idProd = rs.getInt("Id_producto");
                    String producto = rs.getString("Nombre_Producto");
                    String proveedor = rs.getString("Proveedor");
                    boolean ingrediente = rs.getBoolean("Ingrediente");
                    Date fecha = rs.getDate("Fecha_Actualizacion");

                    Stock S = new Stock(id, cant, 0, proveedor, producto, idProd, ingrediente, fecha);
                    lista.add(S);
                }
                 rs.close();
            }
            else if (caso == 3) {
                abrirConexion();
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT S.Id_Stock,(S.Cantidad-S.Usado) AS Stock,S.Proveedor,S.Id_producto,S.Fecha_Actualizacion,P.Nombre_Producto, P.Ingrediente FROM Stocks S  JOIN Productos P ON S.Id_producto = P.Id_Producto WHERE P.Ingrediente = 0");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Stock");
                    int cant = rs.getInt("Stock");
                    int idProd = rs.getInt("Id_producto");
                    String producto = rs.getString("Nombre_Producto");
                    String proveedor = rs.getString("Proveedor");
                    boolean ingrediente = rs.getBoolean("Ingrediente");
                    Date fecha = rs.getDate("Fecha_Actualizacion");

                    Stock S = new Stock(id, cant, 0, proveedor, producto, idProd, ingrediente, fecha);
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
        public ArrayList<Mesa> obtenerMesas()
    {
        ArrayList<Mesa> lista = new ArrayList<>();
        try
        {
           abrirConexion();
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT * FROM Mesas");
                while(rs.next())
                {
                    int id = rs.getInt("Id_Mesas");               
                    String nombre = rs.getString("Nombre");
                    String ubicacion = rs.getString("Ubicacion");
                    Mesa m = new Mesa(id, nombre, ubicacion, 0) ;
                    lista.add(m);
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
                    lista.add(p);
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
    public void addStock(int id, int cant)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DECLARE @USADO INT\n" +
                                                        "DECLARE @CONTROL INT\n" +
                                                        "DECLARE @CANTIDAD INT\n" +
                                                        "BEGIN TRANSACTION\n" +
                                                        "SELECT @USADO = Usado, @CANTIDAD = Cantidad FROM Stocks\n" +
                                                        "WHERE Id_Stock = ?\n" +
                                                        "SET @CANTIDAD = @CANTIDAD + ?\n" +
                                                        "SET @CONTROL= @USADO - ?\n" +
                                                        "IF  @CONTROL>0\n" +
                                                        "BEGIN\n" +
                                                        "	UPDATE Stocks\n" +
                                                        "	SET Usado= @CONTROL\n" +
                                                        "	WHERE Id_Stock = ?\n" +
                                                        "END\n" +
                                                        "ELSE IF @CONTROL = 0\n" +
                                                        "BEGIN\n" +
                                                        "	UPDATE Stocks\n" +
                                                        "	SET usado = 0\n" +
                                                        "	WHERE Id_Stock = ?\n" +
                                                        "	UPDATE Stocks\n" +
                                                        "	SET Cantidad = @CANTIDAD \n" +
                                                        "	WHERE Id_Stock = ?\n" +
                                                        "END\n" +
                                                        "ELSE IF @CONTROL < 0\n" +
                                                        "BEGIN\n" +
                                                        "	SET @CANTIDAD = (@CANTIDAD + @USADO - @CONTROL)\n" +
                                                        "	UPDATE Stocks\n" +
                                                        "	SET usado = 0\n" +
                                                        "	WHERE Id_Stock = ?\n" +
                                                        "	UPDATE Stocks\n" +
                                                        "	SET Cantidad = @CANTIDAD \n" +
                                                        "	WHERE Id_Stock = ?\n" +
                                                        "END\n" +
                                                        "COMMIT TRANSACTION");
            ps.setInt(1,id );
            ps.setInt(2,cant);
            ps.setInt(3,cant);
            ps.setInt(4,id );
            ps.setInt(5,id );
            ps.setInt(6,id );
            ps.setInt(7,id );
            ps.setInt(8,id );
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
    public void quitarStock(int id, int cant)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DECLARE @STOCK INT\n" +
                                                        "BEGIN TRANSACTION\n" +
                                                        "SELECT @STOCK = Usado FROM Stocks\n" +
                                                        "WHERE Id_Stock = ?\n" +
                                                        "SET @STOCK= @STOCK + ?\n" +
                                                        "UPDATE Stocks\n" +
                                                        "SET Usado= @STOCK\n" +
                                                        "WHERE Id_Stock = ?\n" +
                                                        "COMMIT TRANSACTION");
            ps.setInt(1,id );
            ps.setInt(2, cant);
            ps.setInt(3,id);
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
     
    public void actualizarMesa(Mesa M)
    {
       
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Mesas SET Nombre = ?, Ubicacion = ? WHERE Id_Mesas = ?");
            ps.setString(1, M.getNombre());
            ps.setString(2, M.getUbicacion());
            ps.setInt(3, M.getId());
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
      public void agregarTragoxReceta (DTOTragoxReceta T)
    {
       try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Tragos(Nombre_Trago,Disponible,Precio,Id_Categoria) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,T.getTrag().getNombre());
            ps.setBoolean(2,T.getTrag().isDisponible());
            ps.setDouble(3,T.getTrag().getPrecio());
            ps.setInt(4,T.getTrag().getCategoria());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) throw new RuntimeException("no devolvió el ID");

                    int idTrago = rs.getInt(1);
                    for (Producto prod : T.getProd()) 
                    {
                        ps = con.prepareStatement("INSERT INTO Recetas (Id_trago,Id_Producto) VALUES (?,?)");
                        ps.setInt(1,idTrago);
                        ps.setInt(2,prod.getId());
                     
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
    public void actualizarTragoxReceta (DTOTragoxReceta T)
    {
       try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Tragos SET Nombre_Trago = ?, Disponible = ?, Precio = ?, Id_Categoria = ? WHERE Id_Trago = ?");
            ps.setString(1,T.getTrag().getNombre());
            ps.setBoolean(2,T.getTrag().isDisponible());
            ps.setDouble(3,T.getTrag().getPrecio());
            ps.setInt(4,T.getTrag().getCategoria());
            ps.setInt(5,T.getTrag().getId());
            ps.executeUpdate();
            for (Producto prod : T.getProd()) 
                    {
                        ps = con.prepareStatement("UPDATE Recetas SET Id_Producto = ? WHERE Id_trago = ?");
                        ps.setInt(1,prod.getId());
                        ps.setInt(2,T.getTrag().getId());
                     
                        ps.addBatch();
                    }
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


