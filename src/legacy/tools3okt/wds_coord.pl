use strict;
open(g, "wds_19aug14.dat") or die ("=( error");        	# открывает файл file в переменную g
open(h,">", "wds-19aug14-coord.dat") or die ("=( error");
#open(h1,">", "test.dat") or die ("=( error");
#open(h12,">", "CCDM=WDS_nullparam.dat") or die ("=( error");

my($x, %h, @m, $a, $b, $c, $i, $j, $l, $z, $ra, $de, $rho, $theta, $dra, $dde, $ra2, $de2, $r, $t, $ra1, $de1);
$i=0;
$l=0;
$z=0;	
@m=<j>;			#заполнили массив m строчками из файла
#print @m[3];
					
while(defined($x=<g>))				# <g> означает одна строчка из файла g,соотв.здесь цикл бежит по строчкам сверху вниз.	
{
		if($z==0) {$z=$z+1;  
  chomp($x);
  print h ("#  RAJ2000_2  DEJ2000_2  ".$x);
  next;}; 
  
	#next if(length($x)>138);			# переход к следующей строке, если ...
 # $dm1 = substr($x, 27, 7);			# вырезаем кусок из строки. начиная с 13 символа и длиной в 7 символа
  $ra1 = substr($x, 88, 10);
  $de1 = substr($x, 99, 10);
  $rho = substr($x, 64, 7);  $r = $rho* 0.01745/3600;
  $theta = substr($x, 50, 3);
  
  if ($theta>360) {$theta=abs(360-$theta);}
  $ra = $ra1 * 0.01745;
  $de = $de1 * 0.01745;
  $t = $theta * 0.01745;

  $dra = abs($r*sin($t)*cos($de))*57.3;
  $dde = abs($r/(sqrt(1+(sin($t)/cos($t))**2 * cos($de))))*57.3;
  
  
  if (($theta<360)&&($theta>=180)) {$dra= (-1)*$dra}
  if (($theta<270)&&($theta>=90)) {if ($de>0) {$dde= (-1)*$dde}}
  if (($theta<90)||($theta>=270)) {if ($de<0) {$dde= (-1)*$dde}}
  
  $ra2 = $ra1 + $dra;
  $de2 = $de1 + $dde;
 
   # {print h00 ($x);next;}
  chomp($x);
 
 
  printf h (" %8.4f %8.4f %s \n", $ra2, $de2, $x);  
  #printf h1 (sin(90));
  
  
  }
