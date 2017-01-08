use strict;
open(g, "wc_gs1.dat") or die ("=( error");        	# ��������� ���� file � ���������� g
open(h,">", "wc_gs1_p.dat") or die ("=( error");
#open(h11,">", "CCDM=WDS_all.dat") or die ("=( error");
#open(h12,">", "CCDM=WDS_nullparam.dat") or die ("=( error");

my($x, %h, @m, $a, $b, $c, $i, $j, $l,$cm1, $cm2,$s, $z,$dcoord, $d, $m1,$m2,$dm1,$ddt, $dm2, $ddm, $r,$t, $yw1, $yw2, $yc, $rw1, $rw2, $rc, $tw1, $tw2, $tc, $dname, $dr, $dt, $drdt, $dtdt, $fm1, $fm2, $fr, $frt, $ft, $ftt, $fdn, $f, $dm1max,$dm2max,$drmax,$drtmax,$dtmax,$dttmax);
$i=0;
$l=0;
$z=0;	
@m=<j>;			#��������� ������ m ��������� �� �����
#print @m[3];
					
while(defined($x=<g>))				# <g> �������� ���� ������� �� ����� g,�����.����� ���� ����� �� �������� ������ ����.	
{
		if($z==0) {$z=$z+1;  
  chomp($x);
  $l = length($x);
  $s = substr($x, 1, $l-1);
  print h ("#  Fl     D       dRho  dTheta  dRdt  dTdt     dm1      dm2      ddm  ".$s);
  next;};
  
	#next if(length($x)>138);			# ������� � ��������� ������, ���� ...
 # $dm1 = substr($x, 27, 7);			# �������� ����� �� ������. ������� � 13 ������� � ������ � 7 �������

  $cm1 = substr($x, 33, 4);
  $cm2 = substr($x, 42, 4);

  $yc = substr($x, 51, 4);
  $tc = substr($x, 58, 3);
  $rc = substr($x, 65, 6);

  $dname  = substr($x, 124, 5);  
  $dcoord = substr($x, 132, 7);

  $m1 = substr($x, 142, 5);
  $m2 = substr($x, 150, 5);
  
  $yw1 = substr($x, 158, 4);
  $yw2 = substr($x, 165, 4);

  $tw1 = substr($x, 172, 3);
  $tw2 = substr($x, 178, 3);
  
  $rw1 = substr($x, 184, 5);
  $rw2 = substr($x, 192, 5);
  
  $dm1 =$m1-$cm1;
  $dm2 =$m2-$cm2;
  $ddm =$m2-$cm2-$m1+$cm1;
  
  
  if ($dname =~ /\s*true\s*/) { $fdn = 0;};
  if ($dname =~ /\s*false\s*/) { $fdn = 1;};
 
 
  if (abs($yw1-$yc) < (abs($yw2-$yc))) {
     $dr = abs($rw1-$rc);
     $r = $rw1;
     if (abs($tw1-$tc) > 180) {$dt =  360 - abs($tw1-$tc);}
     if (abs($tw1-$tc) <= 180) {$dt =  abs($tw1-$tc);}
     $t = $tw1;
     $drdt = 0;
     $dtdt = 0;
     if(abs($yw1-$yc) != 0) {$drdt = abs($rw1-$rc)/abs($yw1-$yc);
     $dtdt = $dt/abs($yw1-$yc);}  }
 
  if ((abs($yw1-$yc)) >= (abs($yw2-$yc))) {
     $dr =  abs($rw2-$rc);
     $r = $rw2;
     $t = $tw2;
     if (abs($tw2-$tc) > 180) {$dt =  360 - abs($tw2-$tc);}
     if (abs($tw2-$tc) <= 180) {$dt =  abs($tw2-$tc);}
     
     $drdt = 0;
     $dtdt = 0;
     if(abs($yw2-$yc) == 0) { 
		 $drdt = abs($rw2-$rc)/1;
         $dtdt = $dt/1;}   
     
     if(abs($yw2-$yc) != 0) {
		 $drdt = abs($rw2-$rc)/abs($yw2-$yc);
		 $dtdt = $dt/abs($yw2-$yc);}   }
   
   $fm1 = 1;
   $fm2 = 1;
   $fr = 1;
   $frt = 1;
   $ft = 1;
   $ftt = 1;
#m1 =================================
   if ($m1 <= 7) { $dm1max = 0.35*$m1;
     if (abs($dm1) <= 0.35*$m1) {$fm1=0;}
     if (abs($dm1) > 0.35*$m1) {
       if (abs($ddm)<1.5) {$fm1=2;}}}
   
   if ($m1 > 7) { $dm1max = 2.45;
     if (abs($dm1) <= 2.45) {$fm1=0;}
     if (abs($dm1) > 2.45) {
       if (abs($ddm)<1.5) {$fm1=2;}}}
#m2 =================================
   if ($m2 <= 8) { $dm2max = 0.4*$m2-0.75;
     if (abs($dm2) <= 0.4*$m2-0.75) {$fm2=0;}
     if (abs($dm2) > 0.4*$m2-0.75) {
       if (abs($ddm)<1.5) {$fm2=2;}}}
   
   if ($m2 > 8) { $dm2max = 2.45;
     if (abs($dm2) <= 2.45) {$fm2=0;}
     if (abs($dm2) > 2.45) {
       if (abs($ddm)<1.5) {$fm2=2;}}}
# Rho =================================
  if ($r<=6) { $drmax = 2;
    if ($dr <= 2) {$fr=0;}}
  if (($r>6)&&($r<=50)) { $drmax = 41*log($r)/log(10)-30;
    if ($dr <= 41*log($r)/log(10)-30) {$fr=0;}}
  if ($r>50) { $drmax = 40;
    if ($dr <= 40) {$fr=0;}}
       
   if ($drdt <= 4) {$frt=0;}
   $drtmax=4;
# Theta =================================
  if ($tw1>180) {$tw1=abs(360-$tw1)}
  if ($tw2>180) {$tw2=abs(360-$tw2)}
  if ($tc>180) {$tc=abs(360-$tc)}

  if ($dt>=165) { $dtmax = 15; $ddt=180-$dt; 
    if ($r<1) {$ft=0;}
    if ($r>=1) {$ft=2;}}
  if ($dt<165) {$ddt=$dt;
    if ($r<20) {$ft=0;  $dtmax = 165;}
    if (($r>=20)&&($r<500)) {$dtmax = -30*log($r)/log(10)+95; if ($dt<= -30*log($r)/log(10)+95) {$ft=0;}}
    if ($r>=500) {if($dt<=5) {$dtmax = 5; $ft=0;}}}   
   
# dtheta/dt =================================
  if ($r<=0.3) {$dttmax = 20; $ftt=0;}
  if (($r>0.3)&&($r<=300)) {$dttmax = -5.6*log($r)/log(10)+15; if ($dtdt <= -5.6*log($r)/log(10)+15) {$ft=0;}} 
  if ($r>300) {if($dtdt<=1) {$dttmax = 1; $ft=0;}}   
   
   
   
# D    =================================
 
 if ($dm1max == 0) {$dm1max=0.001;}
 if ($dm2max == 0) {$dm2max=0.001;}
 if ($drmax == 0) {$drmax=0.001;}
 if ($dtmax == 0) {$dtmax=0.001;}
 #if ($drtmax == 0) {$drtmax=0.001;}
 if ($dttmax == 0) {$dttmax=0.001;}
 
  if (( $m1 =~ /\s*\"\"\s*/) || ($cm1 =~ /\s*\"\"\s*/)) {$dm1=0;} 
  if (( $m2 =~ /\s*\"\"\s*/) || ($cm2 =~ /\s*\"\"\s*/)) {$dm2=0;} 
  if ($dr =~ /\s*\"\"\s*/) {$dr=0;} 
  if ($drdt =~ /\s*\"\"\s*/) {$drdt=0;} 
  if ($dt =~ /\s*\"\"\s*/) {$dt=0;} 
  if ($dtdt =~ /\s*\"\"\s*/) {$dtdt=0;} 

  $d = sqrt(($dm1/$dm1max)*($dm1/$dm1max) + ($dm2/$dm2max)*($dm2/$dm2max) + ($dr/$drmax)*($dr/$drmax) + ($ddt/$dtmax)*($ddt/$dtmax) + ($drdt/$drtmax)*($drdt/$drtmax) + ($dtdt/$dttmax)*($dtdt/$dttmax) + ($dcoord/100)*($dcoord/100) );
  chomp($x);
    
  if (( $m1 =~ /\s*\"\"\s*/) || ($cm1 =~ /\s*\"\"\s*/)) {$fm1="n";}
  if (( $m2 =~ /\s*\"\"\s*/) || ($cm2 =~ /\s*\"\"\s*/)) {$fm2="n";}
  
  if (( $r =~ /\s*\"\"\s*/) || ($rc =~ /\s*\"\"\s*/)) {$fr="n"; $frt="n";}
  if (( $t =~ /\s*\"\"\s*/) || ($tc =~ /\s*\"\"\s*/)) {$ft="n"; $ftt="n";}

  $f=$fm1. $fm2. $fr. $frt. $ft. $ftt.$fdn;

  printf h ("  %s %9.2f %6.1f %6.0f %6.2f %6.2f %8.2f %8.2f %8.2f %s \n", $f, $d, $dr, $dt, $drdt, $dtdt, $dm1, $dm2, $ddm, $x);  

  
  
  }
