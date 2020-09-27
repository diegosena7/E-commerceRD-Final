import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterSecondaryComponent } from './footer-secondary.component';

describe('FooterSecondaryComponent', () => {
  let component: FooterSecondaryComponent;
  let fixture: ComponentFixture<FooterSecondaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FooterSecondaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterSecondaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
