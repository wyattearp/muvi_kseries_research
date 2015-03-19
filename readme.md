Muvi K-Series Packet Fun
------------------------
I recently picked up a Muvi K-Series camera. Supposedly it works with Android ... but not really with 5.0.1. So I decided to start poking

Initially, I tried to connect with my phone, was unable to connect to the device, but I was able to connect to the wifi and get an IP address. So I fired up my laptop and trusty ```wireshark``` to see what was going on and it was ... nothing. Crud. Ok, let's go back to layer 1, 2 and 3 to see what I'm starting with:

```
[wyatt@yogasploit:~]$ route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         192.168.42.1    0.0.0.0         UG    0      0        0 wlan0
192.168.42.0    0.0.0.0         255.255.255.0   U     9      0        0 wlan0
```

Ok, so let's see what's at ```192.168.42.1```:

```
[wyatt@yogasploit:~]$ nmap -sT 192.168.42.1

Starting Nmap 6.40 ( http://nmap.org ) at 2015-03-18 20:27 EDT
Nmap scan report for 192.168.42.1
Host is up (0.065s latency).
Not shown: 997 closed ports
PORT    STATE SERVICE
53/tcp  open  domain
80/tcp  open  http
554/tcp open  rtsp
```

That's interesting enough. Android loves some http action, let's see what we have there at ```/```:

```
Name 	Size
[DIR] 	DCIM 	-
[DIR] 	html 	-
[DIR] 	live 	-
[DIR] 	mjpeg 	-
[DIR] 	pref 	-
[DIR] 	shutter 	-
```

Oh now that is super cool. Just for grins, let's take a picture and then see what shows up in ```/shutter``` (which is currently empty):

```
Name 	Size
[ ] 	100-0003.jpg 	70K
```

Boom, that's cool and sure enough the image is exactly what I just captured as an image. I'm assuming ```/mpeg``` will do the same basic things but just keep storage of the video files so let's see whats in ```/live/```

```
Name 	Size
[ ] 	precap-1.ts 	link
[ ] 	precap-2.ts 	link
[ ] 	precap-3.ts 	link
[ ] 	precap-4.ts 	link
[ ] 	precap-5.ts 	link
[ ] 	precap-6.ts 	link
[ ] 	precap-7.ts 	link
[ ] 	precap-8.ts 	link
```

Quickly firing these up in VLC didn't really work all the well so I went back to the ```/mpegj``` directory and was met with a constantly stream ```GET``` requests for items that just return a ```404```, sometimes there are a few repeats, but not in this capture:

```
http://192.168.42.1/mjpeg/amba.jpg?ts=1426726587165
http://192.168.42.1/mjpeg/amba.jpg?ts=1426726588550
....
```

Well, no love there so let's take a look at ```/html``` and w00t! there's a ```rstp.html``` file and when we click it we get ... a blank page. LAME! WTF is up with that? Let's see if there's a hint in the source:

``` html
  <script>  
  $(function loadRTSP() {
    var player = document.getElementById("playerId");
    var playlist = player.playlist;
    playlist.clear();
    //var id = playlist.add("rtsp://192.168.42.1/live", "", new Array());
    //playlist.playitem(id);
    setTimeout(function(){
        $("#playerId").hide().show();
    }, 100);
   });
   </script>
```

Well, no wonder it doesn't work and no wonder me clicking on the ```.ts``` streams ... I wasn't going to the right URL! So, I could fix the HTML ... but why fix it when we have VLC? Now we just take the ```rstp://192.168.42.1/live``` and toss it into VLC's open. Current review of the stream shows that it's ~2 sec behind actual live information; that sounds about right.

Well, now that we can stream, let's see what else we can do because JQuery was also listed. Wonder what's in that ```config``` file under ```/prefs```? It's mostly text:

```
video_tab
#record_mode=record_mode_vid
options=record_mode_vid;record_mode_voi;record_mode_lap;record_mode_sel
permission=settable
#video_resolution=1920x1080P 050f 16:09
options=1920x1080P 050f 16:09;1920x1080P 048f 16:09;1920x1080P 025f 16:09;1920x1080P 024f 16:09;1280x0960P 050f 04:03;1280x0960P 048f 04:03;1280x0720P 100f 16:09;1280x0720P 050f 16:09
permission=settable
#video_fov=video_fov_wid
options=video_fov_wid;video_fov_med;video_fov_nar;video_fov_zom
permission=settable
....
```

but there's some odd stuff, like the following:

```
...
00000410  30 33 00 00 00 0a 6f 70  74 69 6f 6e 73 3d 70 68  |03....options=ph|
00000420  6f 74 6f 5f 73 68 6f 74  5f 30 33 00 00 00 3b 70  |oto_shot_03...;p|
00000430  68 6f 74 6f 5f 73 68 6f  74 5f 30 36 00 00 00 3b  |hoto_shot_06...;|
00000440  70 68 6f 74 6f 5f 73 68  6f 74 5f 30 38 00 00 00  |photo_shot_08...|
00000450  0a 70 65 72 6d 69 73 73  69 6f 6e 3d 73 65 74 74  |.permission=sett|
...
000008c0  6c 79 0a 23 6d 61 6e 66  61 63 74 75 72 65 72 3d  |ly.#manfacturer=|
000008d0  00 00 00 00 0a 23 6d 6f  64 75 6c 65 3d 4b 2d 53  |.....#module=K-S|
```

So ... looking at the format, it might just be as simple as the following:
```
#video_resolution=1920x1080P 050f 16:09                 <-- current setting? probably.
options=1920x1080P 050f 16:09;1920x1080P 048f 16:09;... <-- possible settings?
permission=settable                                     <-- if we're allowed to set it?
```