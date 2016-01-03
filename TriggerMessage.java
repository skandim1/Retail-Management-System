package Project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class TriggerMessage {
	private CallableStatement enable_calble_stmt;
	private CallableStatement disable_calble_stmt;
	private CallableStatement show_calble_stmt;


	public TriggerMessage( Connection conn ) throws SQLException
	{
	    enable_calble_stmt  = conn.prepareCall( "begin dbms_output.enable(:1); end;" );
	    disable_calble_stmt = conn.prepareCall( "begin dbms_output.disable; end;" );

	    show_calble_stmt = conn.prepareCall(
	          "declare " +
	          "    l_line varchar2(255); " +
	          "    l_done number; " +
	          "    l_buffer long; " +
	          "begin " +
	          "  loop " +
	          "    exit when length(l_buffer)+255 > :maxbytes OR l_done = 1; " +
	          "    dbms_output.get_line( l_line, l_done ); " +
	          "    l_buffer := l_buffer || l_line || chr(10); " +
	          "  end loop; " +
	          " :done := l_done; " +
	          " :buffer := l_buffer; " +
	          "end;" );
	}

	public void enable( int size ) throws SQLException
	{
	    enable_calble_stmt.setInt( 1, size );
	    enable_calble_stmt.executeUpdate();
	}

	public void disable() throws SQLException
	{
	    disable_calble_stmt.executeUpdate();
	}
	public String show() throws SQLException
	{
	int done = 0;
    String s = "";
	    show_calble_stmt.registerOutParameter( 2, java.sql.Types.INTEGER );
	    show_calble_stmt.registerOutParameter( 3, java.sql.Types.VARCHAR );

	    for(;;)
	    {
	        show_calble_stmt.setInt( 1, 32000 );
	        show_calble_stmt.executeUpdate();
	        s = s + show_calble_stmt.getString(3);
	        if ( (done = show_calble_stmt.getInt(2)) == 1 ) break;
	    }
	    return s;
	}

	public void close() throws SQLException
	{
	    enable_calble_stmt.close();
	    disable_calble_stmt.close();
	    show_calble_stmt.close();
	}


}
