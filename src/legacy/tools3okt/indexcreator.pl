use strict;
open(g, "ccdm_work-29aug14.dat") or die ("=( error");        	# ��������� ���� file � ���������� g
#open(j, "2.dat") or die ("=( error");
open(h,">", "CCDM_work-N-29.dat") or die ("=( error");	# ������ ���� cutten, � ������� ������� ����� ������������ �� ���������� h
#open(h2,">", "basket.dat") or die ("=( error");	# ������ ���� basket, � ������� ������� ����� ������������ �� ���������� h
my($x, $i, $j, $l);
$i=0;
$l=0;
	
#@m=<j>;			#��������� ������ m ��������� �� �����
#print @m[3];
					
while(defined($x=<g>))				# <g> �������� ���� ������� �� ����� g,�����.����� ���� ����� �� �������� ������ ����.	
{
	#next if(length($x)>138);			# ������� � ��������� ������, ���� ...
	#$dm1 = substr($x, 13,  7);			# �������� ����� �� ������. ������� � 13 ������� � ������ � 7 �������
  #$dm2 = substr($x, 20,  7);
  if ($i==0) {chomp($x);  print h ($x."   N ");}
  if ($i!=0) {chomp($x);
  print h ($x."   ".$i);}
  $i++;
 	#$b=substr(@m[$i], 0, 13);
	#if ($b ne $y) {$l++; }
	

  # ����� 1
 

      }
  
  

  
  
#	for($i=0; $i<7359; $i++) 
#	{
# 	$b=substr(@m[$i], 0, 13);
#	if ($b ne $y) {$l++; }
#	}
	
#	if ($l==7359) {
#	chop($x);
#	print h ($x);}
#	$l=0;
#}	

# while(defined($a=<j>)) # �������� ������� ���� � �����, ��� �� ���������� ������ �� ������ ������.				
# {
# #next if(length($a)>138);			
# $b=substr($a, 0, 13);
# 	if ($y eq $b) {$c=substr($x, 0, 138);
#	print h ("$c");}}			
	
	#print h ("$y");
	#$a[$i]=$x;				#��������� ������ � ��������� �� �����.
	#$i=$i+1;
	
	

