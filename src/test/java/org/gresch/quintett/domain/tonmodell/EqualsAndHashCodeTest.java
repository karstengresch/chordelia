package org.gresch.quintett.domain.tonmodell;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-main.xml" })
public class EqualsAndHashCodeTest
{
  @Test
  public void testSetup()
  {
    assertTrue("Spring-Konfiguration sollte geladen werden können", true);
  }
  @Test
  public void testTonEqualityAndHashCode() {
    Ton kleinCisis1 = new Ton(Oktavlage.KLEINE, Name.CISIS);
    Ton kleinCisis2 = new Ton(Oktavlage.KLEINE, Name.CISIS);
    Ton kleinCis1 = new Ton(Oktavlage.KLEINE, Name.CIS);
    assertEquals("Toene sollten gleich sein.", kleinCisis1, kleinCisis2);
    assertEquals("HashCode sollte gleich sein.", kleinCisis1.hashCode(), kleinCisis2.hashCode());
    assertNotSame("Klein CISIS sollte nicht klein CIS sein.", kleinCisis1, kleinCis1);
    
  }

  @Test
  public void testAkkordEqualityAndHashCode() {
    Ton kleinCis1 = new Ton(Oktavlage.KLEINE, Name.CIS);
    Ton kleinD1 = new Ton(Oktavlage.KLEINE, Name.D);
    Ton kleinDis1 = new Ton(Oktavlage.KLEINE, Name.DIS);
    Ton kleinE1 = new Ton(Oktavlage.KLEINE, Name.E);
    List<Ton> clusterListe1 = new ArrayList<Ton>();
    clusterListe1.add(kleinCis1);
    clusterListe1.add(kleinD1);
    clusterListe1.add(kleinDis1);
    clusterListe1.add(kleinE1);
    
    List<Ton> clusterListe2 = new ArrayList<Ton>();
    clusterListe1.add(kleinCis1);
    clusterListe1.add(kleinD1);
    clusterListe1.add(kleinE1);    
    
    Akkord akkordCluster1 = new Akkord();
    akkordCluster1.setTonList(clusterListe1);
    Akkord akkordCluster2 = new Akkord();
    akkordCluster2.setTonList(clusterListe1);
    Akkord akkordCluster3 = new Akkord();
    akkordCluster3.setTonList(clusterListe2);
    assertEquals("Ersten beiden Akkorde sollten gleich sein.", akkordCluster1, akkordCluster2);
    assertEquals("HashCode der ersten beiden Akkorde sollte gleich sein.", akkordCluster1.hashCode(), akkordCluster2.hashCode());
    assertNotSame("Akkord mit weniger Toenen sollte nicht gleich sein.", akkordCluster3, akkordCluster2);
    
    
  }
  
  
}
