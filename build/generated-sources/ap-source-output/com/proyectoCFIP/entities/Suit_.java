package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.ManualSiesa;
import com.proyectoCFIP.entities.SubprocesoSuit;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Suit.class)
public class Suit_ { 

    public static volatile SingularAttribute<Suit, String> nombreSuit;
    public static volatile ListAttribute<Suit, ManualSiesa> manualSiesaList;
    public static volatile SingularAttribute<Suit, Integer> idSuit;
    public static volatile ListAttribute<Suit, SubprocesoSuit> subprocesoSuitList;

}