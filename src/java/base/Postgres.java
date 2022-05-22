/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

/**
 *
 * @author MON ORDI
 */
import java.lang.reflect.*;
import java.sql.*;
import java.util.Vector;


public class Postgres
{
    String condition="";
    boolean afficher=false;
    Connection connection;
    String seq="nextval('seq"+getClass().getSimpleName()+"')";
    
    public String getCondition() {
        return this.condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public void addCondition(String ajout){
        int misyWhere=condition.indexOf("where");
        if(misyWhere>-1){
            String[] split=condition.split("where");
            condition=" where "+ajout+" and "+split[1];
        }else{
            condition=" where "+ajout;
        }
        if(afficher) System.out.println(condition);
    }
    public boolean getAfficher() {
        
        return this.afficher;
    }
    public void setAfficher(boolean afficher) {
        this.afficher = afficher;
    }
    public void setConnection(Connection connection) 
    {
        this.connection = connection;
    }
    public Connection getConnection() {
        return connection;
    }
    public String getSeq() {
        return this.seq;
    }
    public void setSeq(String sq) 
    {
        this.seq = "nextval('seq"+sq+"')";
    }

    // debut 00
    public void setFiltre(int ii) throws Exception 
    {
        Field[] f=getClass().getDeclaredFields();
        int[] iii=new int[f.length];
        for(int i=0;i<f.length;i++)
        {
            if(i==ii){
                iii[i]=1;
                continue;
            }
            iii[i]=0;
        }
        filtre(iii);
    }
    
    public void setFiltreId() throws Exception
    {
        Method met=getClass().getDeclaredMethod("getId");
        if(met.getReturnType().getSimpleName().equalsIgnoreCase("string")){
            String s=(String)met.invoke(this);
            condition=" where id='"+s+"'";
        }else{
            int s=(int)met.invoke(this);
            condition=" where id="+s;
        }
    }
    
    public void setFiltreSeq() throws Exception
    {
        Field[] f=getClass().getDeclaredFields();
        int[] iii=new int[f.length];
        iii[0]=0;
        for(int i=1;i<f.length;i++)
        {
            iii[i]=1;
        }
        filtre(iii);
    }

    public void insert(Connection c) throws Exception
    {
        String nom=getClass().getSimpleName();
        Field[] fields=this.getClass().getDeclaredFields();
        String[] noms=new String[fields.length];
        for(int i=0;i<fields.length;i++)
        {
            noms[i]=fields[i].getName();
        }
        Method[] methods=getClass().getDeclaredMethods();
        String request="insert into "+nom+" values(";
        for(int h=0;h<noms.length;h++)
        {
            for(int j=0;j<methods.length;j++)
            {
                if(methods[j].getName().toLowerCase().equalsIgnoreCase(get(noms[h])))
                {
                    if(isNumber(methods[j].invoke(this))!=null)
                        request+=methods[j].invoke(this);
                    else if(fields[h+1].getType().getSimpleName().equalsIgnoreCase("date")){
                        Date date=new Date(System.currentTimeMillis());
                        for(int zz=0;zz<methods.length;zz++){
                            if(methods[zz].getName().equalsIgnoreCase(get(noms[h])))
                            date=(Date)methods[zz].invoke(this);
                        }
                        // java.util.Date jud=new java.util.Date(System.currentTimeMillis());
                        // int minute=jud.getMinutes();
                        // int second=jud.getSeconds();
                        // int hr=jud.getHours();
                        Integer day=new Integer(date.getDate());
                        Integer month=new Integer(date.getMonth()+1);
                        Integer year=new Integer(date.getYear());
                        request+="'"+new String().valueOf(year+1900)+"-"+new String().valueOf(month)+"-"+new String().valueOf(day)+"'";
                    }
                    else request+="'"+methods[j].invoke(this)+"'";
                    if(h<noms.length-1)request+=",";
                    break;
                }
            }
        }
        request+=")";
        if(afficher)
        System.out.println(request);
        Statement statement=c.createStatement();
        int ret=statement.executeUpdate(request);
        statement.close();
    }
    
    public void insertSeq(Connection c) throws Exception
    {
        String nom=getClass().getSimpleName();
        Field[] fields=this.getClass().getDeclaredFields();
        String[] noms=new String[fields.length-1];
        for(int i=1;i<fields.length;i++)
        {
            noms[i-1]=fields[i].getName();
        }
        Method[] methods=getClass().getDeclaredMethods();
        String request="insert into "+nom+" values(";
        request+=seq+",";
        for(int h=0;h<noms.length;h++)
        {
            for(int j=0;j<methods.length;j++)
            {
                if(methods[j].getName().equalsIgnoreCase(get(noms[h])))
                {
                    if(isNumber(methods[j].invoke(this))!=null)
                        request+=methods[j].invoke(this);
                    else if(fields[h+1].getType().getSimpleName().equalsIgnoreCase("date")){
                        Date date=new Date(System.currentTimeMillis());
                        for(int zz=0;zz<methods.length;zz++){
                            if(methods[zz].getName().equalsIgnoreCase(get(noms[h])))
                            date=(Date)methods[zz].invoke(this);
                        }
                        // java.util.Date jud=new java.util.Date(System.currentTimeMillis());
                        // int minute=jud.getMinutes();
                        // int second=jud.getSeconds();
                        // int hr=jud.getHours();
                        Integer day=new Integer(date.getDate());
                        Integer month=new Integer(date.getMonth()+1);
                        Integer year=new Integer(date.getYear());
                        request+="'"+new String().valueOf(year+1900)+"-"+new String().valueOf(month)+"-"+new String().valueOf(day)+"'";
                    }
                    else request+="'"+methods[j].invoke(this)+"'";
                    if(h<noms.length-1)request+=",";
                    break;
                }
            }
        }
        request+=")";
        if(afficher)
        System.out.println(request);
        Statement statement=c.createStatement();
        int ret=statement.executeUpdate(request);
        statement.close();
    }

    public void delete(Connection c) throws Exception
    {
        String request="delete from "+getClass().getSimpleName()+condition;
        if(afficher)
        System.out.println(request);
        Statement statement=c.createStatement();
        int ret=statement.executeUpdate(request);
        statement.close();
    }
    
    public void update(Connection c) throws Exception
    {
        String request="update "+getClass().getSimpleName()+" set ";
        Field[] fields=getClass().getDeclaredFields();
        Method[] methods=getClass().getDeclaredMethods();
        for(int i=0;i<fields.length;i++)
        {
            for(int j=0;j<methods.length;j++)
            {
                if(get(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                {
                    request+=fields[i].getName()+"=";
                    if(isNumber(methods[j].invoke(this))!=null)request+=methods[j].invoke(this);
                    else if(fields[i].getType().getSimpleName().equalsIgnoreCase("date")){
                        Date date=new Date(System.currentTimeMillis());
                        for(int zz=0;zz<methods.length;zz++){
                            if(methods[zz].getName().equalsIgnoreCase(get(fields[i].getName())))
                            date=(Date)methods[zz].invoke(this);
                        }
                        Integer day=new Integer(date.getDate());
                        Integer month=new Integer(date.getMonth());
                        Integer year=new Integer(date.getYear());
                        request+="'"+new String().valueOf(year+1900)+"-"+new String().valueOf(month)+"-"+new String().valueOf(day)+"'";
                    }
                    else request+="'"+methods[j].invoke(this)+"'";
                    break;
                }
            }
            if(i<fields.length-1)request+=", ";
        }
        request+=condition;
        if(afficher)
        System.out.println(request);
        Statement statement=c.createStatement();
        int ret=statement.executeUpdate(request);
        statement.close();
    }

    public Object[] select(Connection c) throws Exception
    {
        Vector vec=new Vector();
        String req="select * from "+getClass().getSimpleName()+condition;
        if(afficher)
        System.out.println(req);
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(req);
        Method[] resMet=result.getClass().getDeclaredMethods();
        Field[] fields=getClass().getDeclaredFields();
        Method[] methods=getClass().getDeclaredMethods();
        while(result.next())
        {
            Object ob=getClass().newInstance();
            for(int i=0;i<fields.length;i++)
            {
                if(isNumber(fields[i])!=null)
                {
                    String typeNb=numberType(fields[i]);
                    String resMetNom="get"+typeNb;
                    if("getInteger".equalsIgnoreCase(resMetNom))
                    {
                        int n=result.getInt(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getFloat".equalsIgnoreCase(resMetNom))
                    {
                        float n=result.getFloat(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getLong".equalsIgnoreCase(resMetNom))
                    {
                        long n=result.getLong(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getDouble".equalsIgnoreCase(resMetNom))
                    {
                        double n=result.getDouble(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }

                }
                else 
                {
                    if(fields[i].getType().getSimpleName().equalsIgnoreCase("date")){
                        Date date=result.getDate(i+1);
                        for(int j=0;j<methods.length;j++)
                        {   
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName())){
                                methods[j].invoke(ob,date);
                            }
                        }
                    }
                    else{
                        String ss="";
                        ss=result.getString(i+1);
                        for(int j=0;j<methods.length;j++)
                        {   
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,ss);
                                break;
                            }
                        }
                    }
                }
            }
            vec.add(ob);
        }
        Object[] ret=new Object[vec.size()];
        vec.copyInto(ret);
        statement.close();
        result.close();
        return ret;
    }
    public Vector selectV(Connection c) throws Exception
    {
        Vector vec=new Vector();
        String req="select * from "+getClass().getSimpleName()+condition;
        if(afficher)
        System.out.println(req);
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(req);
        Method[] resMet=result.getClass().getDeclaredMethods();
        Field[] fields=getClass().getDeclaredFields();
        Method[] methods=getClass().getDeclaredMethods();
        while(result.next())
        {
            Object ob=getClass().newInstance();
            for(int i=0;i<fields.length;i++)
            {
                if(isNumber(fields[i])!=null)
                {
                    String typeNb=numberType(fields[i]);
                    String resMetNom="get"+typeNb;
                    if("getInteger".equalsIgnoreCase(resMetNom))
                    {
                        int n=result.getInt(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getFloat".equalsIgnoreCase(resMetNom))
                    {
                        float n=result.getFloat(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getLong".equalsIgnoreCase(resMetNom))
                    {
                        long n=result.getLong(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getDouble".equalsIgnoreCase(resMetNom))
                    {
                        double n=result.getDouble(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }

                }
                else 
                {
                    if(fields[i].getType().getSimpleName().equalsIgnoreCase("date")){
                        Date date=result.getDate(i+1);
                        for(int j=0;j<methods.length;j++)
                        {   
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName())){
                                methods[j].invoke(ob,date);
                            }
                        }
                    }
                    else{
                        String ss="";
                        ss=result.getString(i+1);
                        for(int j=0;j<methods.length;j++)
                        {   
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,ss);
                                break;
                            }
                        }
                    }
                }
            }
            vec.add(ob);
        }
        statement.close();
        result.close();
        return vec;
    }

    public Object[] select(Connection c,String req) throws Exception
    {
        if(afficher)
        System.out.println(req);
        Vector vec=new Vector();
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(req);
        Method[] resMet=result.getClass().getDeclaredMethods();
        Field[] fields=getClass().getDeclaredFields();
        Method[] methods=getClass().getDeclaredMethods();
        while(result.next())
        {
            Object ob=getClass().newInstance();
            for(int i=0;i<fields.length;i++)
            {
                if(isNumber(fields[i])!=null)
                {
                    String typeNb=numberType(fields[i]);
                    String resMetNom="get"+typeNb;
                    if("getInteger".equalsIgnoreCase(resMetNom))
                    {
                        int n=result.getInt(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getFloat".equalsIgnoreCase(resMetNom))
                    {
                        float n=result.getFloat(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getLong".equalsIgnoreCase(resMetNom))
                    {
                        long n=result.getLong(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getDouble".equalsIgnoreCase(resMetNom))
                    {
                        double n=result.getDouble(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }

                }
                else 
                {
                    if(fields[i].getType().getSimpleName().equalsIgnoreCase("date")){
                        Date date=result.getDate(i+1);
                        for(int j=0;j<methods.length;j++)
                        {   
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName())){
                                methods[j].invoke(ob,date);
                            }
                        }
                    }
                    else{
                        String ss="";
                        ss=result.getString(i+1);
                        for(int j=0;j<methods.length;j++)
                        {   
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,ss);
                                break;
                            }
                        }
                    }
                }
            }
            vec.add(ob);
        }
        Object[] ret=new Object[vec.size()];
        vec.copyInto(ret);
        statement.close();
        result.close();
        return ret;
    }
    public Vector selectV(Connection c,String req) throws Exception
    {
        if(afficher)
        System.out.println(req);
        Vector vec=new Vector();
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(req);
        Method[] resMet=result.getClass().getDeclaredMethods();
        Field[] fields=getClass().getDeclaredFields();
        Method[] methods=getClass().getDeclaredMethods();
        while(result.next())
        {
            Object ob=getClass().newInstance();
            for(int i=0;i<fields.length;i++)
            {
                if(isNumber(fields[i])!=null)
                {
                    String typeNb=numberType(fields[i]);
                    String resMetNom="get"+typeNb;
                    if("getInteger".equalsIgnoreCase(resMetNom))
                    {
                        int n=result.getInt(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getFloat".equalsIgnoreCase(resMetNom))
                    {
                        float n=result.getFloat(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getLong".equalsIgnoreCase(resMetNom))
                    {
                        long n=result.getLong(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }
                    else if("getDouble".equalsIgnoreCase(resMetNom))
                    {
                        double n=result.getDouble(i+1);
                        for(int j=0;j<methods.length;j++)
                        {
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,n);
                                break;
                            }
                        }
                    }

                }
                else 
                {
                    if(fields[i].getType().getSimpleName().equalsIgnoreCase("date")){
                        Date date=result.getDate(i+1);
                        for(int j=0;j<methods.length;j++)
                        {   
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName())){
                                methods[j].invoke(ob,date);
                            }
                        }
                    }
                    else{
                        String ss="";
                        ss=result.getString(i+1);
                        for(int j=0;j<methods.length;j++)
                        {   
                            if(set(fields[i].getName()).equalsIgnoreCase(methods[j].getName()))
                            {
                                methods[j].invoke(ob,ss);
                                break;
                            }
                        }
                    }
                }
            }
            vec.add(ob);
        }
        statement.close();
        result.close();
        return vec;
    }

    public Object selectOne(Connection c) throws Exception{
        return this.select(c)[0];
    }
    public Object selectOneV(Connection c) throws Exception{
        return this.selectV(c).elementAt(0);
    }

    public Object getAttribut(Connection c,Object ob) throws Exception
    {
        Method[] methods=ob.getClass().getDeclaredMethods();
        Method[] methodss=getClass().getDeclaredMethods();
        String nom=ob.getClass().getSimpleName();
        for(int i=0;i<methods.length;i++){
            if(methods[i].getName().equalsIgnoreCase("setId")){
                for(int k=0;k<methodss.length;k++){
                    if(methodss[k].getName().equalsIgnoreCase("get"+nom)){
                        methods[i].invoke(ob,methodss[k].invoke(this));
                        break;
                    }
                }
                break;
            }
        }
        int iMet=0;
        methods=ob.getClass().getSuperclass().getDeclaredMethods();
        for(int j=0;j<methods.length;j++){
            if(methods[j].getName().equalsIgnoreCase("setFiltreId")){
                methods[j].invoke(ob);
            }else if(methods[j].getName().equalsIgnoreCase("selectOne")){
                iMet=j;
            } 
            
        }
        return methods[iMet].invoke(ob,c);
    }

    public void filtre(int[] condit) throws Exception
    {
        String ret="";
        Field[] field=getClass().getDeclaredFields();
        Method[] method=getClass().getDeclaredMethods();
        String[] noms=new String[condit.length];
        int cp=0;
        for(int w=0;w<condit.length;w++)
        {       
            if(condit[w]!=0)cp++;
        }
        if(cp>=1)
        {
            ret=" where ";
            for(int i=0;i<condit.length;i++)
            {
                if(condit[i]!=0)
                {
                    noms[i]=field[i].getName();
                    ret+=noms[i]+"=";
                    for(int j=0;j<method.length;j++)
                    {
                        if(get(noms[i]).equalsIgnoreCase(method[j].getName()))
                        {
                            if(isNumber(method[j].invoke(this))!=null&&method[j].invoke(this)!=null) ret+=method[j].invoke(this);
                            else if(isNumber(method[j].invoke(this))==null&&method[j].invoke(this)!=null) ret+="'"+method[j].invoke(this)+"'";
                            break;
                        }
                    }
                    if(i<noms.length-1&&cp>=2)ret+=" and ";
                }
            }
        }
        condition=ret;
        if(afficher)
        System.out.println(condition);
    }
    public void recherche() throws Exception
    {
        String ret="";
        Field[] field=getClass().getDeclaredFields();
        Method[] method=getClass().getDeclaredMethods();
        int isany=0;
        for(int i=0;i<field.length;i++)
        {
            String nom=field[i].getName();
            for(int j=0;j<method.length;j++){
                if(("get"+nom.toLowerCase()).compareTo(method[j].getName().toLowerCase())==0){
                    Object o=method[j].invoke(this);
                    if(o!=null&&(field[i].getType().getSimpleName().toLowerCase().compareTo("date")!=0)){     
                        Number nbr=isNumber(method[j].invoke(this));
                        if(nbr==null) isany+=1;
                        else if(nbr!=null){ 
                            String typenbr=nbr.getClass().getSimpleName();
                            typenbr=typenbr.toLowerCase();
                            typenbr=typenbr+"Value";
                            double dbl=new Double(nbr.getClass().getDeclaredMethod(typenbr).invoke(nbr).toString());
                            if(dbl!=0){
                                isany+=1;
                            }
                        }
                    }
                }
            }
        }
        if(isany>=1) ret+=" where ";
        boolean nanampy=false;
        for(int i=0;i<field.length;i++)
        {
            String nom=field[i].getName();
            for(int j=0;j<method.length;j++){
                if(("get"+nom.toLowerCase()).compareTo(method[j].getName().toLowerCase())==0){
                    Object o=method[j].invoke(this);
                    if(o!=null||(field[i].getType().getSimpleName().toLowerCase().compareTo("date")!=0)){
                        Number nbr=isNumber(method[j].invoke(this));
                        if(nbr==null){
                            ret+=field[i].getName()+" like ";
                            ret+="'%"+o.toString()+"%'";
                        }
                        else if(nbr!=null){
                            String typenbr=nbr.getClass().getSimpleName();
                            typenbr=typenbr.toLowerCase();
                            typenbr=typenbr+"Value";
                            double dbl=new Double(nbr.getClass().getDeclaredMethod(typenbr).invoke(nbr).toString());
                            if(dbl!=0){
                                ret+=field[i].getName()+" = ";
                                ret+=o.toString();
                            }
                        }
                        nanampy=true;
                        isany--;
                    }
                    break;
                }
            }
            if(isany>0&&nanampy==true)ret+=" and ";
            nanampy=false;
        }
        condition=ret;
        if(afficher)
        System.out.println(condition);
    }
    public void filtre() throws Exception
    {
        String ret="";
        Field[] field=getClass().getDeclaredFields();
        Method[] method=getClass().getDeclaredMethods();
        int isany=0;
        for(int i=0;i<field.length;i++)
        {
            String nom=field[i].getName();
            for(int j=0;j<method.length;j++){
                if(("get"+nom.toLowerCase()).compareTo(method[j].getName().toLowerCase())==0){
                    Object o=method[j].invoke(this);
                    if(o!=null){
                        Number nbr=isNumber(method[j].invoke(this));
                        if(nbr==null) isany+=1;
                        else if(nbr!=null){ 
                            String typenbr=nbr.getClass().getSimpleName();
                            typenbr=typenbr.toLowerCase();
                            typenbr=typenbr+"Value";
                            double dbl=new Double(nbr.getClass().getDeclaredMethod(typenbr).invoke(nbr).toString());
                            if(dbl!=0){
                                isany+=1;
                            }
                        }
                    }
                }
            }
        }
        if(isany>=1) ret+=" where ";
        boolean nanampy=false;
        for(int i=0;i<field.length;i++)
        {
            String nom=field[i].getName();
            for(int j=0;j<method.length;j++){
                if(("get"+nom.toLowerCase()).compareTo(method[j].getName().toLowerCase())==0){
                    Object o=method[j].invoke(this);
                    if(o!=null){
                        Number nbr=isNumber(method[j].invoke(this));
                        if(nbr==null||(field[i].getType().getSimpleName().toLowerCase().compareTo("date")==0)){
                            ret+=field[i].getName()+" = ";
                            nanampy=true;
                            ret+="'"+o.toString()+"'";
                        } 
                        else if(nbr!=null){ 
                            String typenbr=nbr.getClass().getSimpleName();
                            typenbr=typenbr.toLowerCase();
                            typenbr=typenbr+"Value";
                            double dbl=new Double(nbr.getClass().getDeclaredMethod(typenbr).invoke(nbr).toString());
                            if(dbl!=0){
                                ret+=field[i].getName()+" = ";
                                nanampy=true;
                                ret+=o.toString();
                            }
                        }
                        isany--;
                    }
                    break;
                }
            }
            if(isany>0&&nanampy==true)ret+=" and ";
            nanampy=false;
        }
        condition=ret;
        if(afficher)
        System.out.println(condition);
    }
    public String get(String s)
    {
        return "get"+s.toLowerCase();
    }

    public String set(String s)
    {
        return "set"+s.toLowerCase();
    }

    public Number isNumber(Field field) throws Exception
    {
        Number ret;
        String nom=field.getType().getSimpleName();
        if(nom.equalsIgnoreCase("Integer")||nom.equalsIgnoreCase("int"))ret=new Integer(0);
        else if(nom.equalsIgnoreCase("Float")||nom.equalsIgnoreCase("float"))ret=new Float(0);
        else if(nom.equalsIgnoreCase("Long")||nom.equalsIgnoreCase("long"))ret=new Long(0);
        else if(nom.equalsIgnoreCase("Double")||nom.equalsIgnoreCase("double"))ret=new Double(0);
        else ret=null;
        return ret;
    }

    public String numberType(Field field) throws Exception
    {
        String ret;
        String nom=field.getType().getSimpleName();
        if(nom.equalsIgnoreCase("Integer")||nom.equalsIgnoreCase("int"))ret="Integer";
        else if(nom.equalsIgnoreCase("Float")||nom.equalsIgnoreCase("float"))ret="Float";
        else if(nom.equalsIgnoreCase("Long")||nom.equalsIgnoreCase("long"))ret="Long";
        else if(nom.equalsIgnoreCase("Double")||nom.equalsIgnoreCase("double"))ret="Double";
        else ret="";
        return ret;
    }

    public Number isNumber(Object o) throws Exception
    {
        Number ret;
        String nom=o.getClass().getSimpleName();
        if(nom.equalsIgnoreCase("Integer")||o.getClass().isInstance(new Integer(0)))ret=new Integer((Integer)o);
        else if(nom.equalsIgnoreCase("Float")||o.getClass().isInstance(new Float(0)))ret=new Float((Float)o);
        else if(nom.equalsIgnoreCase("Long")||o.getClass().isInstance(new Long(0)))ret=new Long((Long)o);
        else if(nom.equalsIgnoreCase("Double")||o.getClass().isInstance(new Double(0)))ret=new Double((Double)o);
        else ret=null;
        return ret;
    }
}
