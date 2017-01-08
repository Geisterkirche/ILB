use strict;
open(g, "WDS=CCDM_sort.dat") or die ("=( error");        	# открывает файл file в переменную g
open(h,">", "wc-gsb2-i0.dat") or die ("=( error");	# создаёт файл script, в который пишется вывод отправленный на переменную h
#open(h1,">", "WDS=CCDM_sort_rem.dat") or die ("=( error");	# создаёт файл script, в который пишется вывод отправленный на переменную h
#open(h1, ">", "1.dat") or die ("=( error"); 
my($id, $j, $i, $x, %h, @m,@f,@d, @i2,@im2, $j2,$fl,$j1, $k1,$l1, $a,$D1, $id2, $b, $k, @rh, $l, $dn,$s, $z1, $D,$z,$ds,$aa,$m1, $m2, $r, $rt, $t, $tt,$F,$k,$F1);
$z1 = 0;
$i = "00003+7305 AB ";
#$i2= "00004+7305 AB     ";
$z = 0;
$a=0;
$aa=0;
@i2=();
@im2=();
$fl=0;
while(defined($x=<g>))				# <g> означает одна строчка из файла g,соотв.здесь цикл бежит по строчкам сверху вниз.	
{
		if($z==0) {$z=$z+1;  
			chomp($x);
	print h   ($x);
  next;};
  chomp($x);
  $z1++;
  $m1  = substr($x,  2, 1);
  $m2  = substr($x,  3, 1);
  $r  = substr($x,  4, 1);
  $rt  = substr($x,  5, 1);
  $t  = substr($x,  6, 1);
  $tt  = substr($x,  7, 1);
  $dn  = substr($x,  8, 1);
 

  if ($m1 =~ "n") {$m1=0;}    if ($m1==2) {$m1=0;}
  if ($m2 =~ "n") {$m2=0;}    if ($m2==2) {$m2=0;}
  if ($r =~ "n")   {$r=0;}    if ($r==2)   {$r=0;}
  if ($rt =~ "n") {$rt=0;}    if ($rt==2) {$rt=0;}
  if ($t =~ "n")   {$t=0;}    if ($t==2)   {$t=0;}
  if ($tt =~ "n") {$tt=0;}    if ($tt==2) {$tt=0;}
  
  $F= $m1 + $m2 + $r + $rt + $t + $tt + $dn;
  
  if ($F<=0) {
   print h ($x);} 
  
  }
  


