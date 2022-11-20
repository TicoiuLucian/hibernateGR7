package ro.itschool;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.itschool.entity.Cat;
import ro.itschool.entity.Dog;
import ro.itschool.entity.Fish;
import ro.itschool.entity.Owner;
import ro.itschool.util.HibernateUtil;


//MappedSuperclass – the parent classes, can't be entities (table per child)
//Single Table – The entities from different classes with a common ancestor
// are placed in a single table.
//Joined Table – Each class has its table, and querying a subclass entity requires joining the tables.
//Table per Class – All the properties of a class are in its table, so no join is required.
@Slf4j
public class App {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Dog dog = new Dog();
        dog.setName("Puffy");
        dog.setPrice(350F);
        dog.setHairColour("black");
        dog.setFood(300);

        Fish fish = new Fish();
        fish.setPrice(5.5F);
        fish.setFreshWater(true);
        fish.setNeededSpace(4);
        fish.setName("fish1");
        fish.setFood(2);

        Owner owner = new Owner();
        owner.setName("Vasile");
        owner.setPhoneNumber("0777777777");
        owner.setDog(dog);
        dog.setOwner(owner);

        session.persist(dog);
        session.persist(fish);
        session.persist(owner);

        transaction.commit();
        session.close();


        //-------------------
        session = sessionFactory.openSession();

        final Owner owner1 = session.get(Owner.class, 1);
        final Dog dog1 = session.get(Dog.class, 1);
        log.info(String.valueOf(owner1));
        log.info(dog1.toString());

        session.close();


        //-------------------

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Fish fish1 = new Fish();
        fish1.setFood(3);
        fish1.setName("fish 11");
        fish1.setNeededSpace(4);
        fish1.setFreshWater(true);
        fish1.setPrice(5F);

        Fish fish2 = new Fish();
        fish2.setFood(4);
        fish2.setName("fish 22");
        fish2.setNeededSpace(44);
        fish2.setFreshWater(true);
        fish2.setPrice(6F);

        Fish fish3 = new Fish();
        fish3.setFood(5);
        fish3.setName("fish 33");
        fish3.setNeededSpace(11);
        fish3.setFreshWater(true);
        fish3.setPrice(7F);

        owner.addFishToList(fish1);
        owner.addFishToList(fish2);
        owner.addFishToList(fish3);

        session.persist(fish1);
        session.persist(fish2);
        session.persist(fish3);
        session.merge(owner);
        transaction.commit();
        session.close();

        //--------------------
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Cat cat1 = new Cat();
        cat1.setFood(100);
        cat1.setNapHours(6);
        cat1.setPrice(900F);
        cat1.setName("Tibisor");

        Cat cat2 = new Cat();
        cat2.setFood(150);
        cat2.setNapHours(8);
        cat2.setPrice(700F);
        cat2.setName("Dudu");

        Cat cat3 = new Cat();
        cat3.setFood(120);
        cat3.setNapHours(7);
        cat3.setPrice(500F);
        cat3.setName("Till");

        Cat cat4 = new Cat();
        cat4.setFood(70);
        cat4.setNapHours(5);
        cat4.setPrice(1F);
        cat4.setName("Kitty");

        owner.addCatsToList(cat1);
        owner.addCatsToList(cat2);
        owner.addCatsToList(cat3);

        Owner owner2 = new Owner();
        owner2.setName("Gheorghe");
        owner2.setPhoneNumber("0777777888");
        owner2.addCatsToList(cat2);
        owner2.addCatsToList(cat3);
        owner2.addCatsToList(cat4);

        session.persist(cat1);
        session.persist(cat2);
        session.persist(cat3);
        session.persist(cat4);
        session.merge(owner);
        session.persist(owner2);


        transaction.commit();
        session.close();

//------------Remove parent but don't remove orphans-------------
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Owner o = session.get(Owner.class, 2);
        o.getCats().clear();

        session.merge(o);
        session.remove(o);
        transaction.commit();


        session.close();
        sessionFactory.close();

        System.out.println("hello-github test");
    }
}
