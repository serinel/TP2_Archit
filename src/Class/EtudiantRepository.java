package Class;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Interface.IEtudiant;
import Interface.IEtudiantREPO;
import Interface.IJournal;

public class EtudiantRepository implements IEtudiantREPO {
	private IJournal journal;

	public EtudiantRepository(IJournal Journal){
		this.journal = Journal ;
		}

	@Override
	
	public void add(IEtudiant E) throws SQLException
	{

		
		Connection connect=DBConnection.getConn();
		AfficheDateClass.setSender("EtudiantRepository");
		
		Statement stmt = connect.createStatement();
		String sql = "INSERT into etudiant values (" + E.getMatricule() + ",'" + E.getNom() + "','" + E.getPrenom() + "','" + E.getEmail() + "','" + E.getPwd() + "'," +E.getNbLivreMensuel_Autorise() + "," +E.getNbLivreEmprunte() + "," +E.getId_universite()+")";		int rs = stmt.executeUpdate(sql);
		
		if (rs == 1){
			journal.outPut_Msg("log : ajout dans la BD réussi de l'étudiant  du Matricule" + E.getMatricule());
			}else if (rs == 0){
		    journal.outPut_Msg("log : Echec de l'ajout dans la BD de l'étudiant  du Matricule" + E.getMatricule());
			}
		connect.close();
	 }

	@Override

	public boolean Exists(String email) throws SQLException	
	{
		Connection connect=DBConnection.getConn();
		
		Statement stmt = connect.createStatement();
		String sql = "select * from etudiant where email='"+ email+"'";
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()){
			journal.outPut_Msg("logBD--- :email existe dans la BD  " + email);
			connect.close();
			return true;
			}
		journal.outPut_Msg("logBD--- : email n'existe pas " + email);	
		connect.close();
		return false;
	}
	
	@Override

	public boolean Exists(int mat) throws SQLException	
	{
		Connection connect=DBConnection.getConn();
		
		Statement stmt = connect.createStatement();
		String sql = "select * from etudiant where matricule="+ mat;
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()){

			journal.outPut_Msg("logBD--- :etudiant avec ce matricule existe déja dans la BD  " + mat);
			connect.close();
			return true;
			}
		journal.outPut_Msg("logBD----: etudiant avec ce matricule n'existe pas " + mat);	
		connect.close();
		return false;
	}
	@Override
	public boolean VerifEmailMat(int Mat, String Email) throws SQLException	
	{
		return ( Exists(Email) || Exists(Mat) || Email == null || Email.length() == 0 );

	}

}
