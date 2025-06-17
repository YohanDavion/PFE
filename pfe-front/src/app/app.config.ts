import {ApplicationConfig, isDevMode, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';
import {provideHttpClient, withFetch, withInterceptors} from '@angular/common/http';


import {routes} from './app.routes';
import {provideClientHydration, withEventReplay} from '@angular/platform-browser';
import {provideServiceWorker} from '@angular/service-worker';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {providePrimeNG} from 'primeng/config';
import Aura from '@primeng/themes/aura';
import {AuthInterceptor} from './security/auth-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideHttpClient(withFetch()),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([
        AuthInterceptor
      ])
    ),
    provideClientHydration(withEventReplay()),
    provideAnimationsAsync(),
    provideServiceWorker('ngsw-worker.js',
      {
            enabled: !isDevMode(),
            registrationStrategy: 'registerWhenStable:30000'
      }),
      providePrimeNG({
            theme: {
                preset: Aura,
                options: {
                  prefix: 'p',
                  darkModeSelector: 'system',
                  cssLayer: false
              }
            }
        })]
};
