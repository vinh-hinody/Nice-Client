package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dao.KhachHangServer;
import dao.NhanVienServer;

public class Test_Client {
	
	private static final String URL = "rmi://192.168.3.2:7878/";
	
	public static void main(String[] args) {
		try {
			NhanVienServer cv_dao = (NhanVienServer) Naming.lookup(URL+"NhanVienServer");
			cv_dao.getAll().forEach(t->{
				System.out.println(t);
			});
			
			KhachHangServer kh_dao = (KhachHangServer) Naming.lookup(URL+"KhachHangServer");
			kh_dao.getAll().forEach(t->{
				System.out.println(t);
			});
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
