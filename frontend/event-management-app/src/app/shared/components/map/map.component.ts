import { Component, AfterViewInit } from '@angular/core';
import * as L from 'leaflet';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  standalone: false,
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrl: './map.component.scss'
})
export class MapComponent implements AfterViewInit {

  showIframe = false;
  iframeUrl!: SafeResourceUrl;

  constructor(private sanitizer: DomSanitizer) {}

  ngAfterViewInit(): void {
    const map = L.map('map').setView([42.00465, 21.40903], 18);

     L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '© OpenStreetMap contributors'
    }).addTo(map);

    const marker = L.marker([42.00465, 21.40903]).addTo(map).bindPopup('FINKI UKIM Location');

     map.invalidateSize();

    marker.on('click', () => {
      this.iframeUrl = this.sanitizer.bypassSecurityTrustResourceUrl('https://map.finki.ukim.mk/?l=0#18/42.00465/21.40903');
      this.showIframe = true;
       setTimeout(() => map.invalidateSize(), 300);
    });

        // Open popup on hover
    marker.on('mouseover', () => {
      marker.openPopup();
    });

    // Close popup when mouse leaves
    marker.on('mouseout', () => {
      marker.closePopup();
    });
  }
}
