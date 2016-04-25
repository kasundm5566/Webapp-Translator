<?php
$host = 'localhost:3306';
$user = 'root';
$pass = 'test123';

mysql_connect($host, $user, $pass);

mysql_select_db('userdata');

if(isset($_POST['user_name']))
{
    $name=$_POST['user_name'];

    $checkdata=" SELECT UserName FROM user_cred WHERE name='$name' ";

    $query=mysql_query($checkdata);

    if(mysql_num_rows($query)>0)
    {
        echo "User Name Already Exist";
    }
    else
    {
        echo "OK";
    }
    exit();
}