package com.vektorel.oot.util;

import java.util.ArrayList;
import java.util.List;

public class KisiListe {

	public static List<Ogrenci> ogrenciler=new ArrayList<>();
	
	static{
		ogrenciler.add(new Ogrenci("Ahmet TEST","ODTÜ","Yenimahalle"));
		ogrenciler.add(new Ogrenci("Mehmet TEST","KTU","Yenimahalle"));
		ogrenciler.add(new Ogrenci("Cemal TEST","ODTÜ","Yenimahalle"));
		ogrenciler.add(new Ogrenci("Kemal TEST","GÜ","Yenimahalle"));
	}
	
	
	
}
