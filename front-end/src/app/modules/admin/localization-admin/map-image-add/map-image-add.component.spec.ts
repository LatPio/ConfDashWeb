import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapImageAddComponent } from './map-image-add.component';

describe('MapImageAddComponent', () => {
  let component: MapImageAddComponent;
  let fixture: ComponentFixture<MapImageAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MapImageAddComponent]
    });
    fixture = TestBed.createComponent(MapImageAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
